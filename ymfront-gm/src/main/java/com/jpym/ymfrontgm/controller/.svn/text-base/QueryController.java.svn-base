package com.jpym.ymfrontgm.controller;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.model.*;
import com.jpym.ymfrontgm.requestparammodel.RiskCheckRequestModel;
import com.jpym.ymfrontgm.service.FieldMapperService;
import com.jpym.ymfrontgm.service.QueryService;
import com.jpym.ymfrontgm.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class QueryController {

    @Autowired
    private FieldMapperService fieldMapperService;

    @Autowired
    private QueryService queryService;

    /**
     * @param fundname 基金产品/基金代码
     * @param listtype 列表类型 01:首页优选，02：排行列表，03：定投优选，
     * @param fundtype 基金类型 //0-股票型，1-债券型，2-货币型，3-混合型，4-专户基金，5-指数型，6-QDII
     */
    @GetMapping( value = "/queryFundList" )
    public Result queryFundList(@RequestParam( "fundname" ) String fundname,
                                @RequestParam( "listtype" ) String listtype,
                                @RequestParam( "fundtype" ) String fundtype,
                                @RequestParam( value = "pageindex", defaultValue = "1" ) Number pageindex,
                                @RequestParam( value = "pagesize", defaultValue = MsgConstant.PAGE_SIZE ) Number pagesize) throws Exception {
//        1.调用基金列表查询服务 2.调用字段映射服务
        List fundProductsMapper = fieldMapperService.getNewResults(queryService.queryFundList(fundname, listtype, fundtype), FundListModel.class);
//        3.调用分页工具
        Map pageMap = ResultUtil.resultSetPaging(fundProductsMapper, pagesize.intValue(), pageindex.intValue());
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "基金列表接口成功", pageMap);
        // TODO: 2018/2/1 基金列表 完成待测试
    }

    /**
     * 字典：430306 查询基金详情
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/queryFundDetail" )
    public Result queryFundDetail(@RequestParam( "fundcode" ) String fundcode) throws Exception {
        Map<String, Object> listParamMap = new HashMap<>();
        listParamMap.put("fundcode", fundcode);
        ParamUtil.paramNullCheck(listParamMap);
//        1.调用查询基金详情服务
        Map compositeResult = queryService.queryFundDetail(fundcode);
//        2.调用字段映射服务
        Map finalDetailField = fieldMapperService.getNewResults(compositeResult, FundDetailModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "查询基金详情接口成功", finalDetailField);
        // TODO: 2017/12/26 基金详情 完成待测试
    }

    /**
     * 在途交易
     * 字典：430312 查询历史交易申请 430311 查询可撤单交易申请
     * begindate    开始日期
     * enddate      结束日期
     * timerange    时间区间 m_1-近一个月，m_3-近三个月,y_1-近一年,y_3,近三年
     * businesscode 业务代码 22:申购，24:赎回，59:定投协议开通， 默认空，查询所有
     *
     * @param custno 客户号
     */
    @GetMapping( value = "/queryHistoryTradeOnline" )
    public Result queryHistoryTradeOnline(@RequestParam( value = "pageindex", defaultValue = "1" ) Number pageindex,
                                          @RequestParam( value = "pagesize", defaultValue = MsgConstant.PAGE_SIZE ) Number pagesize,
                                          @RequestParam( "custno" ) String custno) throws Exception {
        Map<String, Object> applyParamMap = new HashMap<>();
        applyParamMap.put("begindate", "00000000");
        applyParamMap.put("enddate", MsgConstant.format.format(new Date()).toString());
        applyParamMap.put("custno", custno);
        ParamUtil.paramNullCheck(applyParamMap);
        applyParamMap.put("businesscode", ""); //22,24,59
//        1.调用历史交易在途服务 2.调用字段映射服务
        List onlineTrades = fieldMapperService.getNewResults(queryService.queryHistoryTradeOnline(applyParamMap), OnlineTradeModel.class);
//        3.调用分页工具
        Map pageMap = ResultUtil.resultSetPaging(onlineTrades, pagesize.intValue(), pageindex.intValue());
        // TODO: 2018/2/1 在途交易 完成待测试
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "在途交易查询成功", pageMap);
    }

    /**
     * 字典 430313 查询历史交易确认
     *
     * @param pageindex 查询页索引
     * @param custno    客户号
     */
    @GetMapping( value = "queryHistoryTradeConfirm" )
    public Result queryHistoryTradeConfirm(@RequestParam( value = "pageindex", defaultValue = "1" ) Number pageindex,
                                           @RequestParam( value = "pagesize", defaultValue = MsgConstant.PAGE_SIZE ) Number pagesize,
                                           @RequestParam( "custno" ) String custno) throws Exception {
        Map<String, Object> confirmParamMap = new HashMap<>();
        confirmParamMap.put("begindate", "00000000");
        confirmParamMap.put("enddate", MsgConstant.format.format(new Date()));
        confirmParamMap.put("custno", custno);
        ParamUtil.paramNullCheck(confirmParamMap);
        confirmParamMap.put("businesscode", ""); //22,24,59
        confirmParamMap.put("flag", "2"); //查询标志 0 查询交易成功， 1 查询交易失败 2 全部查询
//        1.调用历史交易确认服务
        List confirmTradeOrders = queryService.queryHistoryTradeConfirm(confirmParamMap);
//        2.调用分页工具分页
        Map pageMap = ResultUtil.resultSetPaging(confirmTradeOrders, pagesize.intValue(), pageindex.intValue());
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "历史交易确认查询成功", pageMap);
        // TODO: 2018/2/1 历史交易确认 完成待测试
        // FIXME: 2018/3/27 接口的响应速度，会随着客户确认订单数量的增大变慢(接口拿到的是全量数据)
    }

    /**
     * 字典：430316 查询定投协议(查询定投计划列表)
     *
     * @param custno       客户号
     * @param saveplanflag 定投类型
     */
    @GetMapping( value = "/investmentManagement" )
    public Result investmentManagement(@RequestParam( "custno" ) String custno,
                                       @RequestParam( "saveplanflag" ) String saveplanflag,
                                       @RequestParam( value = "pageindex", defaultValue = "1" ) Number pageindex,
                                       @RequestParam( value = "pagesize", defaultValue = MsgConstant.PAGE_SIZE ) Number pagesize) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", custno);
        paramMap.put("saveplanflag", saveplanflag);
        ParamUtil.paramNullCheck(paramMap);
//        1.调用金正定投计划列表
        List fixedInvestmentList = JzInterfaceUtil.jzInvestmentManagement(custno, saveplanflag).getJSONArray(0);
//        2.调用字段映射服务
        List finalAgreementField = fieldMapperService.getNewResults(fixedInvestmentList, FixedInvestmentListModel.class);
//        3.调用分页工具
        Map pageMap = ResultUtil.resultSetPaging(finalAgreementField, pagesize.intValue(), pageindex.intValue());
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "查询定投计划列表成功", pageMap);
        // TODO: 2017/12/26  查询定投列表 内部逻辑处理
    }

    /**
     * 字典: 441303 查询客户号
     *
     * @param certificateno   证件号
     * @param certificatetype 证件类型
     * @param mobiletelno     手机号
     */
    @GetMapping( value = "/queryCustno" )
    public Result queryCustno(@RequestParam( "certificateno" ) String certificateno,
                              @RequestParam( "certificatetype" ) String certificatetype,
                              @RequestParam( "mobiletelno" ) String mobiletelno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("certificateno", certificateno);
        paramMap.put("certificatetype", certificatetype);
        paramMap.put("mobiletelno", mobiletelno);
        ParamUtil.paramNullCheck(paramMap);
//        1.调用金正查询客户号接口
        JSONArray custnoInfo = JzInterfaceUtil.jzQueryCustno(certificateno, certificatetype, mobiletelno).getJSONArray(0);
        if (custnoInfo.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "接口异常，请稍后调用！");
//        2.调用字段映射服务
        Map customerCustno = fieldMapperService.getNewResults(custnoInfo.getJSONObject(0), CustomerCustNoModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "查询客户号成功", customerCustno);
        // TODO: 2017/12/26  查询客户号完成 待检验
    }


    /**
     * 字典: 430333 查询单客户银行卡
     *
     * @param custno 客户号
     */
    @GetMapping( value = "/queryAccountInfoByCustno" )
    public Result queryAccountInfoByCustno(@RequestParam( "custno" ) String custno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", custno);
        ParamUtil.paramNullCheck(paramMap);
//        1.调用金正查询客户
        List custBankCardList = JzInterfaceUtil.jzQuerySingleCustBankCard(custno).getJSONArray(0);
//        2.调用字段映射服务
        List finalBankCardField = fieldMapperService.getNewResults(custBankCardList, CustBankCarListModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "账户信息接口成功", finalBankCardField);
        // TODO: 2017/12/26 查询客户银行卡 完成待检验
    }

    /**
     * 字典  客户分红列表（430313 历史交易确认）
     * businesscode == 43 分红数据
     *
     * @param custno 客户号
     */
    @GetMapping( value = "/queryHistoryBonus" )
    public Result queryHistoryBonus(@RequestParam( "custno" ) String custno,
                                    @RequestParam( value = "pageindex", defaultValue = "1" ) Number pageindex,
                                    @RequestParam( value = "pagesize", defaultValue = "20" ) Number pagesize) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", custno);
        ParamUtil.paramNullCheck(paramMap);
        paramMap.put("begindate", "00000000");
        paramMap.put("enddate", MsgConstant.format.format(new Date()));
        paramMap.put("businesscode", "43"); //业务代码为43，表示的是分红数据
        paramMap.put("paycenterid", "");
        paramMap.put("fundcode", "");
//        1.调用金正客户分红接口
        List historyBoundsArr = JzInterfaceUtil.jzQueryHistoryBonus(paramMap).getJSONArray(0);
//        2.调用字段映射服务
        List finalBoundsField = fieldMapperService.getNewResults(historyBoundsArr, CustHistoryBonusModel.class);
//        3.调用分页工具
        Map pageMap = ResultUtil.resultSetPaging(finalBoundsField, pagesize.intValue(), pageindex.intValue());
        // TODO: 2017/12/26  客户分红列表 完成待检验
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "查询客户历史分红成功", pageMap);
    }

    /**
     * 字典：430304 交易前风险控制检查
     * <p>
     * custno   客户号
     * fundcode 基金代码
     * tano     TA编号
     */
    @PostMapping( value = "/beforeTradeRiskCheck" )
    public Result beforeTradeRiskCheck(@RequestBody RiskCheckRequestModel requestModel) throws Exception {
        Map requestParam = ParamUtil.transBean2Map(requestModel);
        ParamUtil.requestParameterDicCheck(requestParam);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", requestModel.getCustno());
        paramMap.put("fundcode", requestModel.getFundcode());
        paramMap.put("tano", requestModel.getTano());
        ParamUtil.paramNullCheck(paramMap);
//        1.调用金正交易前风险控制检查
        JSONObject riskCheckResult = JzInterfaceUtil.jzBeforeTradeRiskCheck(requestModel.getCustno(), requestModel.getFundcode(), requestModel.getTano(), true);
        if (riskCheckResult.getString("errorCode").equals("0000") || riskCheckResult.getString("success").equals("true"))
            return ResultUtil.success(MsgConstant.SUCCESS_CODE, riskCheckResult.getString("errorMessage"), "");
        return ResultUtil.success(MsgConstant.ERROR_CODE, riskCheckResult.getString("errorMessage"), "");
        // TODO: 2017/12/26 交易前风险检查 完成待检验
    }

    /**
     * 430319 (查询认、申购、赎回费用)
     *
     * @param applicationamount 申请金额
     * @param applicationvol    赎回份额
     * @param businesscode      业务代码
     * @param fundcode          基金代码
     * @param tano              TA编号
     */
    @GetMapping( value = "queryApplyRedemptionFee" )
    public Result queryApplyRedemptionFee(@RequestParam( "applicationamount" ) String applicationamount,
                                          @RequestParam( "applicationvol" ) String applicationvol,
                                          @RequestParam( "businesscode" ) String businesscode,
                                          @RequestParam( "fundcode" ) String fundcode,
                                          @RequestParam( "tano" ) String tano,
                                          @RequestParam( "channelid" ) String channelid) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fundcode", fundcode);
        paramMap.put("tano", tano);
        paramMap.put("businesscode", businesscode);
        paramMap.put("channelid", channelid);
        ParamUtil.paramNullCheck(paramMap);
        paramMap.put("applicationamount", applicationamount);
        paramMap.put("applicationvol", applicationvol);
        paramMap.put("sharetype", "");
        Object jzBody = JzHttpUtil.webApiRequest(paramMap);
        JSONArray jzTradeFee = JSONArray.fromObject(jzBody).getJSONArray(0);
        if (jzTradeFee.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "接口异常，请稍后调用！");
        Map tradeFee = fieldMapperService.getNewResults(jzTradeFee.getJSONObject(0), TradeFeeModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "申购赎回费率", tradeFee);
        // TODO: 2017/12/26  交易费率查询
    }

    /**
     * 查询基金交易费率
     *
     * @param fundcode 基金代码
     */
    @GetMapping( value = "/queryTAtandardFee" )
    public Result queryTAStandardFee(@RequestParam( name = "businesscode" ) String businesscode,
                                     @RequestParam( name = "fundcode" ) String fundcode) throws Exception {
        Map<String, Object> standardFee = new HashMap<>();
        String[] businessTypes = new String[10];
        businessTypes = businesscode.split(",");
        for (String businessType : businessTypes) {
            switch (businessType) {
                case "22": {
                    List applyfee = JzInterfaceUtil.jzQueryTAStandardFee(businessType, fundcode).getJSONArray(0);
                    int applyFeeRangeSize = applyfee.size();
                    if (applyFeeRangeSize != 0) {
                        // FIXME: 2018/3/16 修改费率字段(金正返回数据有误，下一版本修正后，修改本段代码)
                        for (int i = 0; i < applyFeeRangeSize - 1; i++) {
                            JSONObject feeRange = (JSONObject) applyfee.get(i);
                            feeRange.put("feepolicy", 1);
                        }
                        JSONObject feeRange = (JSONObject) applyfee.get(applyFeeRangeSize - 1);
                        feeRange.put("feerate", feeRange.getDouble("feerate") / 100);
                        applyfee = fieldMapperService.getNewResults(applyfee, TATradeFeeModel.class);
                    }
                    standardFee.put("applyfee", applyfee);
                    break;
                }
                case "24": {
                    List redeemfee = JzInterfaceUtil.jzQueryTAStandardFee(businessType, fundcode).getJSONArray(0);
                    if (redeemfee.size() != 0) {
                        redeemfee = fieldMapperService.getNewResults(redeemfee, TATradeFeeModel.class);
                    }
                    standardFee.put("redeemfee", redeemfee);
                    break;
                }
                case "39": {
                    List fixedinvestfee = JzInterfaceUtil.jzQueryTAStandardFee(businessType, fundcode).getJSONArray(0);
                    int applyFeeRangeSize = fixedinvestfee.size();
                    if (applyFeeRangeSize != 0) {
                        // FIXME: 2018/3/16 修改费率字段(金正返回数据有误，下一版本修正后，修改本段代码)
                        for (int i = 0; i < applyFeeRangeSize - 1; i++) {
                            JSONObject feeRange = (JSONObject) fixedinvestfee.get(i);
                            feeRange.put("feepolicy", 1);
                        }
                        JSONObject feeRange = (JSONObject) fixedinvestfee.get(applyFeeRangeSize - 1);
                        feeRange.put("feerate", feeRange.getDouble("feerate") / 100);
                        fixedinvestfee = fieldMapperService.getNewResults(fixedinvestfee, TATradeFeeModel.class);
                    }
                    standardFee.put("fixedinvestfee", fixedinvestfee);
                    break;
                }
                default:
                    break;
            }
        }
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "申购赎回费率", standardFee == null ? Collections.emptyMap() : standardFee);
    }
}