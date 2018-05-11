package com.jpym.ymfrontgm.controller;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.model.*;
import com.jpym.ymfrontgm.requestparammodel.*;
import com.jpym.ymfrontgm.service.FieldMapperService;
import com.jpym.ymfrontgm.service.TradeService;
import com.jpym.ymfrontgm.util.*;
import com.szkingdom.kwis.common.security.DESEncrypt;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TradeController {

    /**
     * 编号: 430002 基金认申购
     * <p>
     * custno 客户号
     * applicationamount 申请金额
     * moneyaccount 资金账户
     * fundcode 基金代码
     * buyflag 强制购买标记
     * tpasswd 交易密码
     * tano TA代码
     */

    @Autowired
    private TradeService tradeService;
    @Autowired
    private FieldMapperService fieldMapperService;

    @PostMapping( value = "/subscribeApply" )
    public Result subscribleApply(@RequestBody SubscribeApplyRequestModel applyModel) throws Exception {
        Map requestParam = ParamUtil.transBean2Map(applyModel);
        ParamUtil.requestParameterDicCheck(requestParam);
        Map<String, Object> applyResult = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", applyModel.getCustno());//
        paramMap.put("moneyaccount", applyModel.getMoneyaccount());//
        paramMap.put("fundcode", applyModel.getFundcode());//
        paramMap.put("buyflag", applyModel.getBuyflag());//
        paramMap.put("tpasswd", applyModel.getTpasswd());
        paramMap.put("tano", applyModel.getTano());
        paramMap.put("channelid", applyModel.getChannelid());
        ParamUtil.paramNullCheck(paramMap);
        paramMap.put("applicationamount", applyModel.getApplicationamount());
        paramMap.put("tpasswd", DESEncrypt.strEnc(applyModel.getTpasswd()));
        paramMap.put("riskwarnflag", "1");
//        1.调用交易Service
        applyResult = tradeService.subscribleApply(paramMap);
        if (applyResult.keySet().size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "申购失败，请稍后发起申购！");
        Map<String, Object> payParamMap = new HashMap<>();
        String appsheetserialno = applyResult.get("appsheetserialno").toString();
        String liqdate = applyResult.get("liqdate").toString();
        payParamMap.put("custno", applyModel.getCustno());
        payParamMap.put("appsheetserialno", appsheetserialno);//原申请单号
        payParamMap.put("moneyaccount", applyModel.getMoneyaccount());
        payParamMap.put("fundcode", applyModel.getFundcode());
        payParamMap.put("fundtype", applyModel.getFundtype());
        payParamMap.put("liqdate", liqdate);//结算日期
//        2.调用支付service
        JSONObject paysendInfo = JzInterfaceUtil.jzPaySend(payParamMap, true);
        if (!paysendInfo.getString("errorCode").equals("0000") && !paysendInfo.getString("errorCode").equals("PPPP"))
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, paysendInfo.getString("errorMessage"));
//        3.调用字段映射Service
        applyResult = fieldMapperService.getNewResults(applyResult, SubscribleApplyModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "申购成功", applyResult);
        // TODO: 2018/2/1  基金认申购 完成待测试
    }


    /**
     * 字典: 430003 基金赎回
     * <p>
     * custno               客户编号
     * transactionaccountid 交易账号
     * applicationamount    申请金额
     * vastredeemflag       巨额赎回标记 0-放弃超额部分，1-继续赎回
     * fundcode             基金代码
     * tpasswd              交易密码
     * tano                 TA代码
     */
    @PostMapping( value = "/redeem" )
    public Result redeem(@RequestBody RedeemRequestModel requestModel) throws Exception {
        Map requestParam = ParamUtil.transBean2Map(requestModel);
        ParamUtil.requestParameterDicCheck(requestParam);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", requestModel.getCustno());
        paramMap.put("transactionaccountid", requestModel.getTransactionaccountid());
        paramMap.put("applicationamount", requestModel.getApplicationamount());
        paramMap.put("vastredeemflag", requestModel.getVastredeemflag());
        paramMap.put("fundcode", requestModel.getFundcode());
        paramMap.put("tpasswd", requestModel.getTpasswd());
        paramMap.put("tano", requestModel.getTano());
        ParamUtil.paramNullCheck(paramMap);
        paramMap.put("tpasswd", DESEncrypt.strEnc(requestModel.getTpasswd()));
        paramMap.put("branchcode", "353");//份额托管网点编号，，查询可赎回基金资产时返回
//        1.调用本地交易Service
        List redeemResultList = tradeService.redeem(paramMap);
//        2.调用本地字段映射服务
        redeemResultList = fieldMapperService.getNewResults(redeemResultList, RedeemModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "赎回成功", redeemResultList);
        // TODO: 2017/12/26  基金赎回 未金正接口验证(没有TA配合)
    }

    /**
     * 字典 430009 交易撤单
     * <p>
     * custno             客户号
     * tpasswd            交易密码
     * originalappsheetno 原申请单编号
     */
    @PostMapping( value = "/custCancelOrder" )
    public Result custCancleOrder(@RequestBody CustCancleOrderRequestModel requestModel) throws Exception {
        Map requestParam = ParamUtil.transBean2Map(requestModel);
        ParamUtil.requestParameterDicCheck(requestParam);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", requestModel.getCustno());
        paramMap.put("tpasswd", requestModel.getTpasswd());
        paramMap.put("originalappsheetno", requestModel.getOriginalappsheetno());
        ParamUtil.paramNullCheck(paramMap);
//        1.调用金正交易撤单接口
        JSONArray custCancleTrade = JzInterfaceUtil.jzCustCancleOrder(requestModel.getCustno(),
                DESEncrypt.strEnc(requestModel.getTpasswd()),
                requestModel.getOriginalappsheetno()).getJSONArray(0);
//        2.调用本地字段映射服务
        List finalOrderField = fieldMapperService.getNewResults(custCancleTrade, CustCancleTradeModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "撤单成功", finalOrderField);
        // TODO: 2017/12/26  交易撤单 完成待测试
    }

    /**
     * 字典 430200 定投开通
     * <p>
     * custno
     * moneyaccount      资金账户
     * firstinvestamount 扣款金额
     * saveplanflag      定投标记
     * investcycle       投资周期
     * investcyclevalue  投资周期类型值
     * fundcode          基金代码
     * tano              Ta编号
     * depositacct       银行卡号
     * channelid
     * buyflag           购买标记
     * riskmatching      风险等级是否匹配
     * tpasswd           交易密码
     */
    @PostMapping( value = "/fixTradeOpen" )
    public Result fixTradeOpen(@RequestBody FixTradeOpenRequestModel requestModel) throws Exception {
        Map requestParam = ParamUtil.transBean2Map(requestModel);
        ParamUtil.requestParameterDicCheck(requestParam);
        List<Map<String, Object>> fixOpenResultMapper = new ArrayList<>();
        List<Map<String, Object>> fixOpenResult = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", requestModel.getCustno());
        paramMap.put("moneyaccount", requestModel.getMoneyaccount());
        paramMap.put("saveplanflag", requestModel.getSaveplanflag());
        paramMap.put("investcycle", requestModel.getInvestcycle()); //0-每月 1-每周 2-每双周
        paramMap.put("investcyclevalue", requestModel.getInvestcyclevalue());//每月：每月X号 每周：周X，每双周：第n个周X 每十天：第X天
        paramMap.put("fundcode", requestModel.getFundcode());
        paramMap.put("depositacct", requestModel.getDepositacct());
        paramMap.put("buyflag", requestModel.getBuyflag());
        paramMap.put("riskmatching", requestModel.getRiskmatching());
        paramMap.put("riskwarnflag", requestModel.getRiskwarnflag());
        paramMap.put("tpasswd", requestModel.getTpasswd());
        paramMap.put("tano", requestModel.getTano());
        paramMap.put("channelid", requestModel.getChannelid());
        ParamUtil.paramNullCheck(paramMap);
        paramMap.put("firstinvestamount", requestModel.getFirstinvestamount());
        paramMap.put("continueinvestamount", requestModel.getFirstinvestamount());
        paramMap.put("tpasswd", DESEncrypt.strEnc(requestModel.getTpasswd()));
        paramMap.put("investmode", "2"); //1-按递增金额扣款,2-按后续投资金额不变
        paramMap.put("operorg", "9999");
//        1.调用本地交易服务
        fixOpenResult.add(tradeService.fixTradeOpen(paramMap));
//        2.调用本地字段映射服务
        fixOpenResultMapper = fieldMapperService.getNewResults(fixOpenResult, FixOpenTradeModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "定投开通成功", fixOpenResultMapper);
        // TODO: 2017/12/26 定投开通 完成待测试
    }

    /**
     * 字典 430204 定投终止
     * <p>
     * custno 客户号
     * buyplanno 定时定额协议号
     * depositacct 银行卡号
     * tpasswd 交易密码
     * transactionaccountid 交易账号
     */
    @PostMapping( value = "/fixInvestmentEndTrade" )
    public Result fixInvestmentEndTrade(@RequestBody FixInvestmentEndRequestModel requestModel) throws Exception {
        Map requestParam = ParamUtil.transBean2Map(requestModel);
        ParamUtil.requestParameterDicCheck(requestParam);
        Map<String, Object> fixInvestmentEnd = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", requestModel.getCustno());
        paramMap.put("buyplanno", requestModel.getBuyplanno());
        paramMap.put("depositacct", requestModel.getDepositacct());
        paramMap.put("tpasswd", requestModel.getTpasswd());
        paramMap.put("transactionaccountid", requestModel.getTransactionaccountid());
        ParamUtil.paramNullCheck(paramMap);
        paramMap.put("tpasswd", DESEncrypt.strEnc(requestModel.getTpasswd()));
        paramMap.put("operorg", "9999");
        paramMap.put("branchcode", "353");
        paramMap.put("channelid", "");
//        1.调用定投终止接口
        JSONArray endFixTrade = JzInterfaceUtil.jzFixInvestmentEndTrade(paramMap).getJSONArray(0);
        if (endFixTrade.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "定投终止失败，请稍后操作！");
//        2.调用字段映射服务
        fixInvestmentEnd = fieldMapperService.getNewResults(endFixTrade.getJSONObject(0), FixInvestmentEndTradeModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "定投终止成功", fixInvestmentEnd);
        // TODO: 2017/12/27 定投终止 完成待校验
    }
}
