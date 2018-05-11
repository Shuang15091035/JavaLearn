package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.datadictionary.FundListDic;
import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.model.ConfirmedOrderModel;
import com.jpym.ymfrontgm.model.FundPerformanceModel;
import com.jpym.ymfrontgm.util.JzInterfaceUtil;
import com.jpym.ymfrontgm.util.MsgConstant;
import com.jpym.ymfrontgm.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class QueryService {
    @Autowired
    FieldMapperService fieldMapperService;
    @Autowired
    GMFundListConfigureService gmFundListConfigureService;
    @Autowired
    FundPerformanceService fundPerformanceService;
    @Autowired
    private FundHistoryNavService fundHistoryNavService;

    public List queryFundList(String fundname, String listType, String fundType) throws Exception {

        List<Map<String, Object>> fundProducts = new ArrayList<>();
        if (listType.getBytes("utf-8").length > 6)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[listType]入参数据缓冲区太小");
        if (fundname.length() > 32)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[fundname]入参数据缓冲区太小");
        listType = StringUtil.notEmpty(fundname) ? "" : listType;
        Class fundlistCls = MsgConstant.fundListDicMap.get(FundListDic.queryCurrentDic(listType));
        FundListLg fundListLg = (FundListLg) fundlistCls.getConstructor(String.class, String.class, String.class).newInstance(fundname, listType, fundType);
        fundProducts = fundListLg.fundlistByFundListDic();
        //获取三方数据（日涨幅)
        Integer fundProductSize = fundProducts.size();
        for (int i = 0; i < fundProductSize; i++) {
            JSONObject currentFund = (JSONObject) fundProducts.get(i);
            currentFund.putAll(filterThirdDataFundNameField(currentFund.getString("fundcode")));
        }
        return fundProducts;
    }

    public Map queryFundDetail(String fundcode) throws Exception {
        Map<String, Object> responseResult = new HashedMap();
        String fundCode = fundcode;
        JSONArray jzDataSet = JzInterfaceUtil.jzQueryFundList("", fundCode, "").getJSONArray(0);
        if (jzDataSet.size() != 0) {
            JSONObject currentFund = jzDataSet.getJSONObject(0);
            // FIXME: 2018/3/23 二期基金状态可能会有特殊更改
            String fundStatus = currentFund.getString("status");
            String availableSubscriptionStatus = "0678";
            boolean isMatch = availableSubscriptionStatus.contains(fundStatus);
            currentFund.put("subscriptionstatus", isMatch ? "01" : "00");
            String fundBusinessFlag = currentFund.getString("business_flag");
            currentFund.put("fixedinveststate", isMatch && fundBusinessFlag.contains("1") ? "01" : "00");
            responseResult.putAll(currentFund);
            JSONArray detaliArr = JzInterfaceUtil.jzQueryFundDetali(fundCode).getJSONArray(0);
            if (detaliArr.size() != 0) {
                JSONObject jzFundDetail = detaliArr.getJSONObject(0);
                responseResult.putAll(jzFundDetail);
            }
        }
        responseResult.putAll(filterThirdDataFundNameField(fundCode));
        return responseResult;
    }

    private Map filterThirdDataFundNameField(String fundcode) throws Exception {
        Map thirdData = queryThirdData(fundcode);
        if (thirdData != null) {
            thirdData.remove("fundname");
            thirdData.putAll(thirdData);
        }
        return thirdData;
    }

    private Map queryThirdData(String fundCode) throws Exception {
        Map fieldAddMap = new HashMap();
        try {
            //获取三方数据（日涨幅)
            Object currentFundNativeNav = fundHistoryNavService.searchFundHistoryNavByPaging(fundCode, 1, 2);
            JSONArray currentFundNav = JSONArray.fromObject(currentFundNativeNav);
            if (currentFundNav.size() != 0) {
                JSONObject fundnav = currentFundNav.getJSONObject(0);
                fieldAddMap.putAll(fundnav);
            }
            //查询近三月收益率字段
            FundPerformanceModel currentFundYeildRate = fundPerformanceService.searchFundPerformance(fundCode);
            if (currentFundYeildRate != null) {
                JSONObject fundYeild = JSONObject.fromObject(currentFundYeildRate);
                fieldAddMap.putAll(fundYeild);
            }
        } catch (BadSqlGrammarException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return fieldAddMap;
    }

    public List queryHistoryTradeOnline(Map<String, Object> applyParamMap) throws Exception {
        List<Map<String, Object>> OnlineTradeList = new ArrayList<>();
        JSONArray applyArr = JzInterfaceUtil.jzQueryHistoryTradeApplay(applyParamMap).getJSONArray(0);
        String custno = applyParamMap.get("custno").toString();
        //2.可撤单交易列表
        JSONArray cancleTradeArr = JzInterfaceUtil.jzQueryWithdrawalOrderList(custno).getJSONArray(0);
        Integer applySize = applyArr.size();
        //状态：status委托状态定义：00待复核；01待勾兑；02待报；04废单；05已撤；06已报；07已确认；08已结束
        for (int i = 0; i < applySize; i++) {
            JSONObject tradeOrder = applyArr.getJSONObject(i);
//           02：待报不等同于可撤单(网上交易控制有关，和支付结果没有关系) (存在可撤单列表中，为可撤单，否则为不可撤单,)
            String status = tradeOrder.getString("status");
            switch (status) {
                case "02": {
                    Iterator cancleTradeOrder = cancleTradeArr.iterator();
                    while (cancleTradeOrder.hasNext()) {
                        JSONObject order = JSONObject.fromObject(cancleTradeOrder.next());
                        if (order.getString("appsheetserialno").trim().equals(tradeOrder.getString("appsheetserialno").trim())) {
//                            tradeOrder.put("statusname", "可撤单");
                            tradeOrder.put("cancelstatus", "01"); // 可撤单标记 00：不可撤单，01可撤单
                            tradeOrder.put("date", tradeOrder.getString("operdate"));
                            OnlineTradeList.add(tradeOrder);
                            break;
                        }
                    }
//            1.通过定投协议下单内容不可撤单，eg:该委托为系统下单，不允许撤单！
                    if (!OnlineTradeList.contains(tradeOrder)) {
                        tradeOrder.put("cancelstatus", "00"); // 可撤单标记 00：不可撤单，01可撤单
                        tradeOrder.put("date", tradeOrder.getString("operdate"));
                        OnlineTradeList.add(tradeOrder);
                    }
                    break;
                }
//                eg:下了单未支付，收市以后，在导出上报文件之后就变为废单
//                case "04" : {
//                    tradeOrder.put("statusname", "废单");
//                    tradeOrder.put("date",tradeOrder.getString("operdate"));
//                    OnlineTradeList.add(tradeOrder);
//                    break;
//                }
//                case "05" : {
//                    tradeOrder.put("statusname", "已撤单");
//                    tradeOrder.put("date",tradeOrder.getString("operdate"));
//                    OnlineTradeList.add(tradeOrder);
//                    break;
//                }
                case "06": {
//                    tradeOrder.put("statusname", "在途");
                    tradeOrder.put("date", tradeOrder.getString("operdate"));
                    tradeOrder.put("cancelstatus", "00"); // 可撤单标记 00：不可撤单，01可撤单
                    OnlineTradeList.add(tradeOrder);
                    break;
                }
//                case "08": {
//                    String returncode = tradeOrder.getString("returncode");
//                    switch (returncode) {
//                        case "0000" :{
//                            tradeOrder.put("statusname", "确认成功已结束");
//                            break;
//                        }
//                        default: {
//                            tradeOrder.put("statusname", tradeOrder.getString("returnmsg"));
//                            break;
//                        }
//                    }
//                    tradeOrder.put("date",tradeOrder.getString("transactioncfmdate"));
//                    OnlineTradeList.add(tradeOrder);
//                    break;
//                }
                default:
                    break;
            }
        }

        return OnlineTradeList;
    }


    public List queryHistoryTradeConfirm(Map<String, Object> confirmParamMap) throws Exception {
        String custno = confirmParamMap.get("custno").toString();
        List tradeConfirmAndcancled = new ArrayList();
        //历史交易确认
        JSONArray jzConfirmArr = JzInterfaceUtil.jzQueryHistoryTradeConfirm(confirmParamMap).getJSONArray(0);
        if (jzConfirmArr.size() != 0) {
            List<Map<String, Object>> confirmTrade = new ArrayList();
            for (Object tradeObj : jzConfirmArr) {
                JSONObject alterObj = JSONObject.fromObject(tradeObj);
                String businesscode = alterObj.getString("businesscode");
                Pattern p = Pattern.compile("22|24|39|42|44|45|50");
                Matcher m = p.matcher(businesscode);
                if (m.matches()) {
                    String returnCode = alterObj.getString("returncode");
                    if (returnCode.equals("0000")) {
                        alterObj.put("statusname", "确认成功");
                        alterObj.put("date", alterObj.getString("transactioncfmdate")); //此处为确认日期
                    } else {
                        alterObj.put("statusname", "确认失败");
                        alterObj.put("date", alterObj.getString("transactioncfmdate")); //此处为确认日期
                    }
                    confirmTrade.add(alterObj);
                }
            }
            if (confirmTrade.size() != 0) {
                confirmTrade = fieldMapperService.getNewResults(confirmTrade, ConfirmedOrderModel.class);
                tradeConfirmAndcancled.addAll(confirmTrade);
            }
        }
        //历史交易已撤单
        Map<String, Object> applyParamMap = new HashMap<>();
        applyParamMap.put("begindate", "00000000");
        applyParamMap.put("enddate", MsgConstant.format.format(new Date()));
        // FIXME: 2018/3/23 本期查询历史交易记录的业务，后期可能为修改
        applyParamMap.put("businesscode", "22,24");
        applyParamMap.put("custno", custno);
        applyParamMap.put("status", "05");
        JSONArray jzCancledOrder = JzInterfaceUtil.jzQueryHistoryTradeApplay(applyParamMap).getJSONArray(0);
        if (jzCancledOrder.size() != 0) {
            List<Map<String, Object>> cancledTrade = new ArrayList();
            for (Object tradeObj : jzCancledOrder) {
                JSONObject alterObj = JSONObject.fromObject(tradeObj);
                alterObj.put("statusname", "已撤单");
                alterObj.put("date", alterObj.getString("operdate")); //此处为操作日期
                alterObj.put("confirmedamount", "0.00");
                cancledTrade.add(alterObj);
            }
            if (cancledTrade.size() != 0) {
                cancledTrade = fieldMapperService.getNewResults(cancledTrade, ConfirmedOrderModel.class);
                tradeConfirmAndcancled.addAll(cancledTrade);
            }
        }
        return tradeConfirmAndcancled;
    }
}
