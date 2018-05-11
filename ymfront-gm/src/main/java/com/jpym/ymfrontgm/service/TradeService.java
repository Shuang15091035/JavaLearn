package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.datadictionary.TradeType;
import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.util.JzInterfaceUtil;
import com.jpym.ymfrontgm.util.MsgConstant;
import com.jpym.ymfrontgm.util.ParamUtil;
import com.jpym.ymfrontgm.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TradeService {

    @Autowired
    private QueryService queryService;
    @Autowired
    private AccountService accountService;

    public Map subscribleApply(Map<String, Object> paramMap) throws Exception {
//        1.申购净额必须为极差的整数倍，2.申购金额满足首次最低，申购追加最低，申购最低中的最大值；
//         首次最高，申购追加最高，申购最高中的最小值,3.判断产品状态(status)
        Double applicationamount = ((Number) paramMap.get("applicationamount")).doubleValue();
        judgeStepUnitApplyAmountAndFundStatus(paramMap, applicationamount, TradeType.TradeType_22);
        JSONArray subscribeResult = JzInterfaceUtil.jzSubscribleApply(paramMap).getJSONArray(0);
        if (subscribeResult.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "申购操作失败，请稍后操作！");
        return subscribeResult.getJSONObject(0);
    }

    /**
     * 定投service
     *
     * @param paramMap 定投入参
     */
    public JSONObject fixTradeOpen(Map<String, Object> paramMap) throws Exception {
        Double firstinvestamount = ((Number) paramMap.get("firstinvestamount")).doubleValue();
        judgeStepUnitApplyAmountAndFundStatus(paramMap, firstinvestamount, TradeType.TradeType_39);
        String investcycle = paramMap.get("investcycle").toString();
        String investcyclevalue = paramMap.get("investcyclevalue").toString();
        String firstinvestdate = "";
        if ("0".equals(investcycle)) {
            paramMap.put("investperiods", "0"); //0-每月 1-周 1-双周 指明按月还是按周定投
            paramMap.put("investperiodsvalue", "1"); // 1-每月 1-每周 2-每双周 每周还是每双周
            Pattern p = Pattern.compile("0[1-9]|1\\d|2[0-8]");
            Matcher m = p.matcher(investcyclevalue);
            if (!m.matches())
                throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[investcyclevalue]值不正确");
            JSONArray firstEffectiveDate = JzInterfaceUtil.jzQueryFixTradeFirstEffectiveTime(investcycle, investcyclevalue, "1").getJSONArray(0);
            if (firstEffectiveDate.size() == 0)
                throw new YmfrontGmException(MsgConstant.ERROR_CODE, "接口异常，请稍后操作！");
            firstinvestdate = firstEffectiveDate.getJSONObject(0).getString("firstinvestdate");
        }
        if ("1".equals(investcycle)) {
            paramMap.put("investperiods", "1"); //0-每月 1-周 1-双周 指明按月还是按周定投
            paramMap.put("investperiodsvalue", "1"); // 1-每月 1-每周 2-每双周 每周还是每双周
            Pattern p = Pattern.compile("0[1-5]");
            Matcher m = p.matcher(investcyclevalue);
            if (!m.matches())
                throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[investcyclevalue]值不正确");
            JSONArray firstEffectiveDate = JzInterfaceUtil.jzQueryFixTradeFirstEffectiveTime(investcycle, investcyclevalue, "1").getJSONArray(0);
            if (firstEffectiveDate.size() == 0)
                throw new YmfrontGmException(MsgConstant.ERROR_CODE, "接口异常，请稍后操作！");
            firstinvestdate = firstEffectiveDate.getJSONObject(0).getString("firstinvestdate");
        }
        if ("2".equals(investcycle)) {
            paramMap.put("investperiods", "1"); //0-每月 1-周 1-双周 指明按月还是按周定投
            paramMap.put("investperiodsvalue", "2"); // 1-每月 1-每周 2-每双周 每周还是每双周
            Pattern p = Pattern.compile("0[1-5]");
            Matcher m = p.matcher(investcyclevalue);
            if (!m.matches())
                throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[investcyclevalue]值不正确");
            JSONArray firstEffectiveDate = JzInterfaceUtil.jzQueryFixTradeFirstEffectiveTime(investcycle, investcyclevalue, "2").getJSONArray(0);
            if (firstEffectiveDate.size() == 0)
                throw new YmfrontGmException(MsgConstant.ERROR_CODE, "接口异常，请稍后操作！");
            firstinvestdate = firstEffectiveDate.getJSONObject(0).getString("firstinvestdate");
        } else {
            new YmfrontGmException(MsgConstant.ERROR_CODE, "【investcycle】参数值不正确");
        }
        paramMap.put("firstinvestdate", firstinvestdate);
        JSONArray fixTradeOpen = JzInterfaceUtil.jzFixTradeOpen(paramMap).getJSONArray(0);
        if (fixTradeOpen.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "接口异常，请稍后开通定投！");
        JSONObject fixedOpen = fixTradeOpen.getJSONObject(0);
        //定投开通后，协议状态默认为未知，需要一下signSend(定投签约接口)
        JzInterfaceUtil.jzFixOpenTradeSignSend(paramMap.get("moneyaccount").toString(),
                paramMap.get("custno").toString(),
                fixedOpen.getString("appsheetserialno"),
                fixedOpen.getString("buyplanno"));
        return fixedOpen;
    }


    /**
     * //1.申购净额必须为极差的整数倍，2.申购金额满足首次最低，申购追加最低，申购最低中的最大值；
     * 首次最高，申购追加最高，申购最高中的最小值,3.判断产品状态(status)
     *
     * @param paramMap
     * @param applyAmount
     * @throws Exception
     */
    private void judgeStepUnitApplyAmountAndFundStatus(Map<String, Object> paramMap, Double applyAmount, TradeType tradeType) throws Exception {
        //        1.申购金额必须为极差的整数倍
        String fundcode = paramMap.get("fundcode").toString();
        JSONArray currentProduct = JSONArray.fromObject(queryService.queryFundDetail(fundcode));
        if (currentProduct.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "接口异常，请稍后开通定投！");
        JSONObject singleProduct = currentProduct.getJSONObject(0);
        if (tradeType == TradeType.TradeType_22) {
            String stepUnit22 = singleProduct.getString("step_unit_22");
            if (StringUtil.notEmpty(stepUnit22) && Integer.parseInt(stepUnit22) != 0) {
                if (applyAmount % Integer.parseInt(stepUnit22) != 0)
                    throw new YmfrontGmException(MsgConstant.ERROR_CODE, "申请金额" + applyAmount + "必须为极差的整数倍");
            }
        }
        // FIXME: 2018/3/23 二期首次和追加申购值可能不同需要走不同逻辑
//        2.申购金额满足首次最低，申购追加最低，申购最低三个值得最大值首次最高，申购追加最高，申购最高中的最小值,
        String firstPerMin22 = "0";
        String firstPerMax22 = "0";
        String conPerMin22 = "0";
        String conPerMax22 = "0";
        if (tradeType == TradeType.TradeType_22) {
            firstPerMin22 = singleProduct.getString("first_per_min_" + tradeType.getIndex());
            firstPerMax22 = singleProduct.getString("first_per_max_" + tradeType.getIndex());
            conPerMin22 = singleProduct.getString("con_per_min_" + tradeType.getIndex());
            conPerMax22 = singleProduct.getString("con_per_max_" + tradeType.getIndex());
        }
        String perMin22 = singleProduct.getString("per_min_" + tradeType.getIndex());
        Double applyAmountMin = ParamUtil.getMaxValueThreeObj(firstPerMin22, conPerMin22, perMin22);
        String perMax22 = singleProduct.getString("per_max_" + tradeType.getIndex());
        Double applyAmountMax = ParamUtil.getMaxValueThreeObj(firstPerMax22, conPerMax22, perMax22);
        if (applyAmountMin != applyAmountMax)
            if (applyAmount < applyAmountMin || applyAmount > applyAmountMax)
                throw new YmfrontGmException(MsgConstant.ERROR_CODE, "申请金额" + applyAmount + "必须大于申购最小值" + new BigDecimal(applyAmountMin) + "小于申购最大值" + new BigDecimal(applyAmountMax));
//        3.判断产品状态(status)
        // FIXME: 2018/3/23 二期基金状态可能会有特殊更改
        String fundStatus = singleProduct.getString("status");
        String availableSubscriptionStatus = "0678";
        boolean isMatch = availableSubscriptionStatus.contains(fundStatus);
        if (!isMatch) {
            //当日基金状态 0-交易，1-发行 2-发行成功，3-发行 失败 4-基金停止交易 5-停止申购 6-停止赎回 7-权益登记 8-红利发放 9-基金封闭，a-基金终止
            List<Map<String, Object>> dictList = ParamUtil.querydict("130011");
            for (Map<String, Object> dmap : dictList) {
                if (fundStatus.equals(dmap.get("subitem"))) {
                    throw new YmfrontGmException(MsgConstant.ERROR_CODE, "基金状态:" + dmap.get("subitem") + "基金状态名称:" + dmap.get("subitemname"));
                }
            }
        }
    }


    /**
     * 赎回service
     *
     * @param paramMap 入参
     */
    public List redeem(Map<String, Object> paramMap) throws Exception {
        Float applicationamount = Float.parseFloat(paramMap.get("applicationamount").toString());
        String fundcode = paramMap.get("fundcode").toString();
        //1.查询基金详情信息获取到相应的 holdmin，status,
        JSONArray fundDetail = JzInterfaceUtil.jzQueryFundList("",fundcode,"").getJSONArray(0);
        if (fundDetail.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "当前产品不存在！");
        JSONObject fundProduct = fundDetail.getJSONObject(0);
        //当日基金状态 0-交易，1-发行 2-发行成功，3-发行 失败 4-基金停止交易 5-停止申购 6-停止赎回 7-权益登记 8-红利发放 9-基金封闭，a-基金终止
        String fundStatus = fundProduct.getString("status");
        String availableRedeemStatus = "0578";
        boolean isMatch = availableRedeemStatus.contains(fundStatus);
        if (!isMatch)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "当前基金不支持赎回");
//        2.查询基金详情，获取持有总份额-applicationamount == 0 或 获取持有总份额-applicationamount >= holdmin
        float holdmin = Float.parseFloat(fundProduct.getString("holdmin")); //最低持有份额
//        3.查询客户资产
        // flag 查询货币基金资产信息,Flag == 2 )//查询非货币基金资产信息,你要想全部查，可以入一个9.
        Map fundAsset = accountService.productAssetAllocationDetail(paramMap.get("custno").toString(), fundcode, "9");
        JSONArray bankCardAsset = JSONArray.fromObject(fundAsset.get("fundBankAsset"));
        if (bankCardAsset.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "内部异常，请稍后操作！");
        for (Object bankCard : bankCardAsset) {
            JSONObject bankCardObj = JSONObject.fromObject(bankCard);
            if (bankCardObj.getString("transactionaccountid").trim().equals(paramMap.get("transactionaccountid").toString().trim())) {
                float totalavailablemodel = Float.parseFloat(bankCardObj.getString("totalavailablemodel"));
                if ((totalavailablemodel - applicationamount < holdmin && totalavailablemodel - applicationamount > 0)) {
                    throw new YmfrontGmException(MsgConstant.ERROR_CODE, "最低留存份额不小于" + "[" + holdmin + "]");
                }
            }
        }
//       目前本接口调用查询资产详情接口查询交易账号可用份额，查询基金详情接口获取最低持有，
        return JzInterfaceUtil.jzRedeem(paramMap).getJSONArray(0);
        // TODO: 2017/12/26  基金赎回 完成待验证
    }
}
