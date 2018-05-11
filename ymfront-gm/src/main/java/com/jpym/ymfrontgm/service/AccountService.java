package com.jpym.ymfrontgm.service;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.model.FundAssetAllocationModel;
import com.jpym.ymfrontgm.model.FundAssetDetailBankCardModel;
import com.jpym.ymfrontgm.model.FundAssetDetailProductModel;
import com.jpym.ymfrontgm.model.TotalAssetAllocationModel;
import com.jpym.ymfrontgm.util.JzInterfaceUtil;
import com.jpym.ymfrontgm.util.MsgConstant;
import com.jpym.ymfrontgm.util.ResultUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private FieldMapperService fieldMapperService;

    /**
     * 客户适当性校验
     * 投资者准入协议签署
     *
     * @param custno 客户号
     */
    public boolean custAdapterAgreementSignedCheck(String custno) throws Exception {
        JSONArray custInfo = JzInterfaceUtil.jzQueryCustInfo(custno).getJSONArray(0);
        if (custInfo.size() == 0) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "内部错误，请稍后开户");
        }
        String identifyflag = custInfo.getJSONObject(0).getString("identifyflag");
        String mobileno = custInfo.getJSONObject(0).getString("mobileno");
        if (identifyflag.equals("0")) {
            JSONArray agreementSigned = JzInterfaceUtil.jzInvestorAccessAgreementSigned(custno, mobileno, "", true);
            JSONObject signedResult = agreementSigned.getJSONObject(0);
            if (signedResult.size() == 0 || !signedResult.getString("errorCode").equals("0000"))
                throw new YmfrontGmException(MsgConstant.ERROR_CODE, "内部错误，请稍后开户");
        }
        return true;
    }

    public Map assetAllocation(String custno, String flag, Number pageindex, Number pagesize) throws Exception {
        List<Map<String, Object>> fundAssetList = new ArrayList<>();
        Map<String, Object> pageMap = new HashMap<>();
        Map<String, Object> commonFundCodeMap = new HashMap<>();
        Map<String, Object> totalAssetArr = new HashMap<>();
        JSONArray jzBody = JzInterfaceUtil.jzAccountAssetAllocation(custno, flag);
        JSONArray jzTotalAsset1 = jzBody.getJSONArray(0);
        //将获取的基金产品根据fundcode进行分组
        int tradeAssetSize = jzTotalAsset1.size();
        String fundCode = null;
        for (int i = 0; i < tradeAssetSize; i++) {
            fundCode = jzTotalAsset1.getJSONObject(i).getString("fundcode");
            if (commonFundCodeMap.containsKey(fundCode)) {
                ArrayList fundArr = (ArrayList) commonFundCodeMap.get(fundCode);
                fundArr.add(jzTotalAsset1.getJSONObject(i));
            } else {
                List<Map<String, Object>> fundProduct = new ArrayList<>();
                fundProduct.add(jzTotalAsset1.getJSONObject(i));
                commonFundCodeMap.put(jzTotalAsset1.getJSONObject(i).getString("fundcode"), fundProduct);
            }
        }
        //同一只基金的 持仓市值，和累计收益进行汇总
        for (String key : commonFundCodeMap.keySet()) {
            double fundTotalBalance = .0f; //当前基金的总市值
            double fundTotalIncome = 0.0f; //当前基金总收益
            double fundTotalYesincome = 0.0f; //当前基金总昨日收益
            List commonFundCode = (List) commonFundCodeMap.get(key);
            int commonFundSize = commonFundCode.size();
            for (int i = 0; i < commonFundSize; i++) {
                JSONObject product = JSONObject.fromObject(commonFundCode.get(i));
                fundTotalBalance += product.getDouble("fundmarketvalue_mode1");
                fundTotalIncome += product.getDouble("addincome");
                fundTotalYesincome += product.getDouble("yestincome");
            }
            if (commonFundSize != 0) {
                Map<String, Object> currentFundAsset = new HashMap<>();
                currentFundAsset.putAll(JSONObject.fromObject(commonFundCode.get(0)));
                currentFundAsset.put("fundtotalvolbalance_mode1", fundTotalBalance);
                currentFundAsset.put("fundtotaladdincome", fundTotalIncome);
                currentFundAsset.put("fundtotalyesincome", fundTotalYesincome);
                fundAssetList.add(currentFundAsset);
            }
        }

        //当前客户的总收益进行汇总
        JSONArray jzTotalAsset3 = jzBody.getJSONArray(2);
        Map<String, Object> totalAssetMap = new HashMap<>();
        if (tradeAssetSize != 0) {
            totalAssetMap.putAll(jzTotalAsset1.getJSONObject(0));
        }
        if (jzTotalAsset3.size() != 0) {
            totalAssetMap.putAll(jzTotalAsset3.getJSONObject(0));
        }
        totalAssetMap = fieldMapperService.getNewResults(totalAssetMap, TotalAssetAllocationModel.class);
        totalAssetArr.put("totalAsset", totalAssetMap);
        fundAssetList = fieldMapperService.getNewResults(fundAssetList, FundAssetAllocationModel.class);
        pageMap = ResultUtil.resultSetPaging(fundAssetList, pagesize.intValue(), pageindex.intValue());
        totalAssetArr.put("fundAsset", pageMap);
        return totalAssetArr;
    }

    /**
     * 产品资产详情
     *
     * @param custno   客户号
     * @param fundcode 基金代码
     * @param flag     资产信息标识
     */
    @GetMapping( value = "/productAssetAllocationDetail" )
    public Map productAssetAllocationDetail(String custno, String fundcode, String flag) throws Exception {
        Map<String, Object> fundAsset = new HashMap<>();
        Map<String, Object> fundCodeAsset = new HashMap<>();
        List<Map<String, Object>> fundBankAsset = new ArrayList<>();
        List<Map<String, Object>> commonFundCode = new ArrayList<>();
        Map<String, Object> bankCardMap = new HashMap<>();
        JSONArray jzBody = JzInterfaceUtil.jzAccountAssetAllocation(custno, flag);
        JSONArray jzFundAsset = jzBody.getJSONArray(0);
        String currentFundCode = fundcode;
        String jzFundCode = null;
        //符合fundcode的所有基金的产品
        for (int i = 0; i < jzFundAsset.size(); i++) {
            jzFundCode = jzFundAsset.getJSONObject(i).getString("fundcode");
            if (currentFundCode.equals(jzFundCode)) {
                // FIXME: 2018/3/23 二期基金状态可能会有特殊更改
                JSONObject currentFund = jzFundAsset.getJSONObject(i);
                String fundStatus = currentFund.getString("status");
                String availableRedeemStatus = "0578";
                boolean isMatch = availableRedeemStatus.contains(fundStatus);
                if (isMatch) {
                    currentFund.put("redeemstatus", "01");
                } else {
                    currentFund.put("redeemstatus", "00");
                }
                commonFundCode.add(currentFund);
            }
        }
        //将获取的基金产品根据同卡进行分组
        int fundAssetSize = commonFundCode.size();
        String depositacct = null;
        for (int i = 0; i < fundAssetSize; i++) {
            depositacct = commonFundCode.get(i).get("depositacct").toString();
            if (bankCardMap.containsKey(depositacct)) {
                ArrayList fundArr = (ArrayList) bankCardMap.get(depositacct);
                fundArr.add(commonFundCode.get(i));
            } else {
                List<Map<String, Object>> fundProduct = new ArrayList<>();
                fundProduct.add(commonFundCode.get(i));
                bankCardMap.put(commonFundCode.get(i).get("depositacct").toString(), fundProduct);
            }
        }
        //单张银行卡所有基金份额的总和
        double allBankCardIncome = 0.0; //当前基金总收益
        for (String key : bankCardMap.keySet()) {
            double totalAvailableFundVol = .0f;
            double totalfundvolbalanceMode1 = .0f;
            Map<String, Object> currentBankFundAsset = new HashMap<>();
            List commonCardFund = (List) bankCardMap.get(key);
            int commonCardFundSize = commonCardFund.size();
            for (int i = 0; i < commonCardFundSize; i++) {
                JSONObject product = JSONObject.fromObject(commonCardFund.get(i));
                totalAvailableFundVol += product.getDouble("availablevol_mode1");
                totalfundvolbalanceMode1 += product.getDouble("fundvolbalance_mode1");
                allBankCardIncome += product.getDouble("addincome");
            }
            if (commonCardFundSize != 0) {
                currentBankFundAsset.putAll(JSONObject.fromObject(commonCardFund.get(0)));
                currentBankFundAsset.put("totalavailablemodel", totalAvailableFundVol);
                currentBankFundAsset.put("totalfundvolbalance_mode1", totalfundvolbalanceMode1);
                fundBankAsset.add(currentBankFundAsset);
            }
        }
        if (fundBankAsset.size() != 0) {
            fundBankAsset = fieldMapperService.getNewResults(fundBankAsset, FundAssetDetailBankCardModel.class);
        }
        //所有银行卡的份额的总和
        int allBankCardSize = fundBankAsset.size();
        double allCardFundBalance = .0f;
        for (int i = 0; i < allBankCardSize; i++) {
            JSONObject product = JSONObject.fromObject(fundBankAsset.get(i));
            allCardFundBalance += product.getDouble("totalfundvolbalance_mode1");
        }
        if (fundAssetSize != 0) {
            Map<String, Object> currentFundAsset = new HashMap<>();
            currentFundAsset.putAll(commonFundCode.get(0));
            currentFundAsset.put("AllBankCardfundvolbalance", allCardFundBalance);
            currentFundAsset.put("AllBankCardAddincome", allBankCardIncome);
            fundCodeAsset = fieldMapperService.getNewResults(currentFundAsset, FundAssetDetailProductModel.class);
        }
        fundAsset.put("fundCodeAsset", fundCodeAsset);
        fundAsset.put("fundBankAsset", fundBankAsset);
        return fundAsset;
    }
}

