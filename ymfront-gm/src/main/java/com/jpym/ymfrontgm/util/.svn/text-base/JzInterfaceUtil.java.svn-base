package com.jpym.ymfrontgm.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JzInterfaceUtil {

//  ---------------------------------------------金正接口账户接口相关------------------------------------------------------------------------------------------

    /**
     * 字典 430100 客户开户
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzCustomerOpenAccount(Map requestParam) throws Exception {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("trantype", "430100");
        paramMap.put("invtp", requestParam.get("invtp")); //客户类型 0-机构，1-个人 ,目前版本只支持传入1
        paramMap.put("custname", requestParam.get("custname"));
        paramMap.put("certificateno", requestParam.get("certificateno"));
        paramMap.put("certificatetype", requestParam.get("certificatetype")); //目前只支持证件类型为身份证
        paramMap.put("sex", requestParam.get("sex")); //[110059]0-女 1-男
        paramMap.put("country", requestParam.get("country")); //[110049] 156-中国
        paramMap.put("address", requestParam.get("address")); //住址
        paramMap.put("vailddate", requestParam.get("vailddate")); // 证件有效期
        paramMap.put("vocationcode", requestParam.get("vocationcode")); // [100011]投资者职业代码
        paramMap.put("channelid", requestParam.get("channelid")); //传入的值为channelid
        paramMap.put("depositacct", requestParam.get("depositacct"));
        paramMap.put("authenticateflag", requestParam.get("authenticateflag")); //鉴权标志只能传1
        paramMap.put("mobileno", requestParam.get("mobileno"));
        paramMap.put("tpasswd", requestParam.get("tpasswd"));
        paramMap.put("channelid", requestParam.get("channelid"));
        paramMap.put("channelname", requestParam.get("channelname"));
        paramMap.put("bankname", requestParam.get("bankname"));
        paramMap.put("custfullname", requestParam.get("custfullname"));
        paramMap.put("depositacctname", requestParam.get("depositacctname")); //银行账户名，一般是客户姓名
        paramMap.put("depositname", requestParam.get("depositname"));
        paramMap.put("operorg", requestParam.get("operorg"));
        paramMap.put("authenticateflag", requestParam.get("authenticateflag")); //0-未鉴权，1-已鉴权
        paramMap.put("tradingmethod", requestParam.get("tradingmethod")); //2-网上交易
        paramMap.put("telno", ""); //电话和手机好不能同时为空，这里电话号默认为空
        paramMap.put("annualincome", "");
        paramMap.put("custmanagerid", "");
        paramMap.put("custrisk", "");
        paramMap.put("delivertype", "");
        paramMap.put("deliverway", "");
        paramMap.put("depositcity", "");
        paramMap.put("depositprov", "");
        paramMap.put("educationlevel", "");
        paramMap.put("email", "");
        paramMap.put("familyname", "");
        paramMap.put("fax", "");
        paramMap.put("firstname", "");
        paramMap.put("hometelno", "");
        paramMap.put("instrepridcode", "");
        paramMap.put("instrepname", "");
        paramMap.put("investorsbirthday", "");
        paramMap.put("ismainback", "");
        paramMap.put("ismainpay", "");
        paramMap.put("lpasswd", "");
        paramMap.put("minorflag", "");
        paramMap.put("minorid", "");
        paramMap.put("nickname", "");
        paramMap.put("officetelno", "");
        paramMap.put("pcertificatetype", "");
        paramMap.put("postcode", "");
        paramMap.put("referral", "");
        paramMap.put("referralcityname", "");
        paramMap.put("referralprovincename", "");
        paramMap.put("shsecuritiesaccountid", "");
        paramMap.put("szsecuritiesaccountid", "");
        paramMap.put("taccountid", "");
        paramMap.put("tano", "");
        paramMap.put("transactorcertno", "");
        paramMap.put("transactorcertrefer", "");
        paramMap.put("transactorcerttype", "");
        paramMap.put("transactorname", "");
        paramMap.put("transactorvalidate", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 bgMsgSend 短信鉴权发送
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzBgMsgSend(Map requestParam) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "bgMsgSend");
        paramMap.put("depositacctname", requestParam.get("depositacctname"));
        paramMap.put("certificatetype", requestParam.get("certificatetype"));
        paramMap.put("certificateno", requestParam.get("certificateno"));
        paramMap.put("channelid", requestParam.get("channelid"));
        paramMap.put("depositacct", requestParam.get("depositacct"));
        paramMap.put("mobiletelno", requestParam.get("mobiletelno"));
        paramMap.put("subbankno", requestParam.get("subbankno"));
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 bgMsgCheckOnly 短信鉴权确认
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzBgMsgCheck(Map requestParam) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "bgMsgCheckOnly");
        paramMap.put("certificatetype", requestParam.get("certificatetype"));
        paramMap.put("certificateno", requestParam.get("certificateno"));
        paramMap.put("depositacctname", requestParam.get("depositacctname"));
        paramMap.put("channelid", requestParam.get("channelid")); //传入的值为channelid
        paramMap.put("depositacct", requestParam.get("depositacct"));
        paramMap.put("mobiletelno", requestParam.get("mobiletelno"));
        paramMap.put("verificationCode", requestParam.get("verificationCode"));
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430104 添加银行卡
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzAddBankCard(Map requestParam) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430104");
        paramMap.put("custno", requestParam.get("custno"));
        paramMap.put("certificatetype", requestParam.get("certificatetype"));
        paramMap.put("certificateno", requestParam.get("certificateno"));
        paramMap.put("depositacctname", requestParam.get("depositacctname"));
        paramMap.put("channelid", requestParam.get("channelid"));
        paramMap.put("depositacct", requestParam.get("depositacct"));
        paramMap.put("mobiletelno", requestParam.get("mobiletelno"));
        paramMap.put("tpasswd", requestParam.get("tpasswd")); //只有柜台设置了增卡不需要校验密码的话，才能传空值
        paramMap.put("authenticateflag", requestParam.get("authenticateflag")); //0-未鉴权，1-已鉴权
        paramMap.put("tpasswd", requestParam.get("tpasswd"));
        paramMap.put("channelid", requestParam.get("channelid"));
        paramMap.put("channelname", requestParam.get("channelname"));
        paramMap.put("bankname", requestParam.get("bankname"));
        paramMap.put("operorg", requestParam.get("operorg"));
        paramMap.put("depositname", " ");
        paramMap.put("depositcity", " ");
        paramMap.put("depositprov", " ");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

//    ---------------------------------------------金正查询接口相关------------------------------------------------------------------------------------------

    /**
     * 字典 430434 查询TA标准费率
     *
     * @param businesscode 业务代码
     * @param fundcode     基金代码
     */
    public static JSONArray jzQueryTAStandardFee(String businesscode, String fundcode) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430434");
        paramMap.put("businesscode", businesscode);
        paramMap.put("fundcode", fundcode);
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430304 交易前风险控制检查
     *
     * @param custno   客户号
     * @param fundcode 基金代码
     * @param tano     TA代码
     */
    public static JSONObject jzBeforeTradeRiskCheck(String custno, String fundcode, String tano, boolean isAllData) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430304");
        paramMap.put("custno", custno);
        paramMap.put("fundcode", fundcode);
        paramMap.put("tano", tano);
        return JSONObject.fromObject(JzHttpUtil.webApiRequest(paramMap, isAllData));
    }

    /**
     * 字典 430313 查询历史分红
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzQueryHistoryBonus(Map requestParam) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430313");
        paramMap.put("custno", requestParam.get("custno"));
        paramMap.put("fundcode", requestParam.get("fundcode"));
        paramMap.put("begindate", requestParam.get("begindate"));
        paramMap.put("enddate", requestParam.get("enddate"));
        paramMap.put("businesscode", requestParam.get("businesscode")); //业务代码为43，表示的是分红数据
        paramMap.put("paycenterid", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430303 查询客户信息
     *
     * @param custno 客户号
     */
    public static JSONArray jzQueryCustInfo(String custno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430303");
        paramMap.put("custno", custno);
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典: 430333 查询单客户银行卡
     *
     * @param custno 客户号
     */
    public static JSONArray jzQuerySingleCustBankCard(String custno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430333");
        paramMap.put("custno", custno);
        paramMap.put("moneyaccount", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 441303 查询客户号
     *
     * @param certificateno   证件号码
     * @param certificatetype 证件类型
     * @param mobiletelno     手机号码
     */
    public static JSONArray jzQueryCustno(String certificateno, String certificatetype, String mobiletelno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "441303");
        paramMap.put("certificateno", certificateno);
        paramMap.put("certificatetype", certificatetype);
        paramMap.put("mobiletelno", mobiletelno);
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 441302 查询定投预计首次执行时间
     *
     * @param investcycle      投资周期类型
     * @param investcycleValue 投资周期类型值
     */
    public static JSONArray jzQueryFixTradeFirstEffectiveTime(String investcycle, String investcycleValue, String investperiodsvalue) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "441302");
        paramMap.put("investcycle", investcycle); //0-每月 1-每周 2-每双周
        paramMap.put("investcycleValue", investcycleValue); //每月：每月X号 每周：周X，每双周：第n个周X 每十天：第X天
        paramMap.put("investperiodsvalue", investperiodsvalue); // 1-月 1-每周 2-每双周 区分双周的第几个周
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430316 定投计划列表
     *
     * @param custno       客户号
     * @param saveplanflag 定投类型
     */
    public static JSONArray jzInvestmentManagement(String custno, String saveplanflag) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430316");
        paramMap.put("custno", custno);
        paramMap.put("saveplanflag", saveplanflag);
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430312 历史交易申请
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzQueryHistoryTradeApplay(Map requestParam) throws Exception {
        Map<String, Object> applyParamMap = new HashMap<>();
        applyParamMap.put("trantype", "430312");
        applyParamMap.put("begindate", requestParam.get("begindate"));
        applyParamMap.put("enddate", requestParam.get("enddate"));
        applyParamMap.put("businesscode", requestParam.get("businesscode"));
        applyParamMap.put("custno", requestParam.get("custno"));
        applyParamMap.put("status", "");
        applyParamMap.put("paycenterid", "");
        applyParamMap.put("fundcode", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(applyParamMap));
    }

    /**
     * 字典 430311 查询可撤单交易申请
     *
     * @param custno 客户号
     */
    public static JSONArray jzQueryWithdrawalOrderList(String custno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430311");
        paramMap.put("custno", custno);
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 历史交易确认
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzQueryHistoryTradeConfirm(Map requestParam) throws Exception {
        Map<String, Object> pageMap = new HashMap<>();
        List<Map<String, Object>> confirmTradeOrders = new ArrayList<>();
        Map<String, Object> confirmParamMap = new HashMap<>();
        confirmParamMap.put("trantype", "430313");
        confirmParamMap.put("custno", requestParam.get("custno"));
        confirmParamMap.put("begindate", requestParam.get("begindate"));
        confirmParamMap.put("enddate", requestParam.get("enddate"));
        confirmParamMap.put("businesscode", requestParam.get("businesscode"));
        confirmParamMap.put("flag", requestParam.get("flag")); //查询标志 0 查询交易成功， 1 查询交易失败 2 全部查询
        confirmParamMap.put("paycenterid", "");
        confirmParamMap.put("fundcode", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(confirmParamMap));
    }

    /**
     * 字典 430306 查询基金列表
     *
     * @param fundtype 基金类型
     * @param fundcode 基金代码
     * @param busiflag 业务标识
     */
    public static JSONArray jzQueryFundList(String fundtype, String fundcode, String busiflag) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430306");
        paramMap.put("fundtype", fundtype);
        paramMap.put("busiflag", busiflag);
        paramMap.put("fundcode", fundcode);
        paramMap.put("mktflag", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430307 查询基金详情
     *
     * @param fundcode 基金代码
     */
    public static JSONArray jzQueryFundDetali(String fundcode) throws Exception {
        Map<String, Object> detailParamMap = new HashMap<>();
        detailParamMap.put("trantype", "430307");
        detailParamMap.put("fundcode", fundcode);
        detailParamMap.put("tano", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(detailParamMap));
    }

    /**
     * 字典：430305 查询基金资产 （公募持仓）
     *
     * @param custno 客户号
     * @param flag   查询资产信息的标识
     */
    public static JSONArray jzAccountAssetAllocation(String custno, String flag) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430305");
        paramMap.put("custno", custno);
        paramMap.put("flag", flag);//Flag == 0 查询货币基金资产信息；Flag == 2 查询非货币基金资产信息；Flag == 9 你要想全部查.
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430555代销资产收益查询接口
     * @param custno 客户号
     */
    public static JSONArray jzQueryCustAssetIncome(String custno) throws Exception {
        Map<String,Object> custAssetParam = new HashMap<>();
        custAssetParam.put("trantype","430555");
        custAssetParam.put("custno",custno);
        custAssetParam.put("flag","");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(custAssetParam));
    }

    /**
     * 字典 430556代销每日收益明细查询接口
     * @param custno 客户号
     */
    public static JSONArray jzQueryEveryDayIncomeDetail(String custno) throws Exception {
        Map<String,Object> eachIncomeParam = new HashMap<>();
        eachIncomeParam.put("trantype","430556");
        eachIncomeParam.put("custno",custno);
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(eachIncomeParam));
    }

//    ---------------------------------------------金正交易接口相关------------------------------------------------------------------------------------------

    /**
     * 字典 430002 基金申购
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzSubscribleApply(Map requestParam) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430002");
        paramMap.put("custno", requestParam.get("custno"));
        paramMap.put("moneyaccount", requestParam.get("moneyaccount"));
        paramMap.put("fundcode", requestParam.get("fundcode"));
        paramMap.put("buyflag", requestParam.get("buyflag"));
        paramMap.put("tpasswd", requestParam.get("tpasswd"));
        paramMap.put("tano", requestParam.get("tano"));
        paramMap.put("channelid", requestParam.get("channelid"));
        paramMap.put("applicationamount", requestParam.get("applicationamount"));
        paramMap.put("riskwarnflag", requestParam.get("riskwarnflag"));
        paramMap.put("custmanagerid", "");
        paramMap.put("operorg", "");
        paramMap.put("referral", "");
        paramMap.put("referralcity", "");
        paramMap.put("referralprov", "");
        paramMap.put("referralmobile", "");
        paramMap.put("reinvestflag", "");
        paramMap.put("targetfundcode", "");
        paramMap.put("targettano", "");
        paramMap.put("transactionaccountid", "");
        paramMap.put("transactorcertno", "");
        paramMap.put("transactorcerttype", "");
        paramMap.put("transactorname", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 paySend 申购支付接口
     *
     * @param requestParam 可变请求参数
     */
    public static JSONObject jzPaySend(Map requestParam, Boolean isAllData) throws Exception {
        Map<String, Object> zfmap = new HashMap();
        zfmap.put("trantype", "paySend");
        zfmap.put("custno", requestParam.get("custno"));
        zfmap.put("appsheetserialno", requestParam.get("appsheetserialno"));//原申请单号
        zfmap.put("moneyaccount", requestParam.get("moneyaccount"));
        zfmap.put("fundcode", requestParam.get("fundcode"));
        zfmap.put("fundtype", requestParam.get("fundtype"));
        zfmap.put("liqdate", requestParam.get("liqdate"));//结算日期
        return JSONObject.fromObject(JzHttpUtil.webApiRequest(zfmap, isAllData));
    }

    /**
     * 字典 430003 交易赎回
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzRedeem(Map requestParam) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430003");
        paramMap.put("custno", requestParam.get("custno"));
        paramMap.put("transactionaccountid", requestParam.get("transactionaccountid"));
        paramMap.put("applicationamount", requestParam.get("applicationamount"));
        paramMap.put("vastredeemflag", requestParam.get("vastredeemflag"));
        paramMap.put("fundcode", requestParam.get("fundcode"));
        paramMap.put("tpasswd", requestParam.get("tpasswd"));
        paramMap.put("tano", requestParam.get("tano"));
        paramMap.put("branchcode", requestParam.get("branchcode"));
        paramMap.put("originappsheetserialno", "");
        paramMap.put("transactorcertno", "");
        paramMap.put("transactorcerttype", "");
        paramMap.put("transactorcertname", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430009 客户撤单
     *
     * @param custno             客户号
     * @param tpasswd            交易密码
     * @param originalappsheetno
     */
    public static JSONArray jzCustCancleOrder(String custno, String tpasswd, String originalappsheetno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430009");
        paramMap.put("custno", custno);
        paramMap.put("tpasswd", tpasswd);
        paramMap.put("originalappsheetno", originalappsheetno);
        paramMap.put("transactorcertno", "");
        paramMap.put("transactorcerttype", "");
        paramMap.put("transactorname", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430200 定投开通
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzFixTradeOpen(Map requestParam) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430200");
        paramMap.put("custno", requestParam.get("custno"));
        paramMap.put("moneyaccount", requestParam.get("moneyaccount"));
        paramMap.put("saveplanflag", requestParam.get("saveplanflag"));
        paramMap.put("investcycle", requestParam.get("investcycle")); //0-每月 1-每周 2-每双周
        paramMap.put("investcyclevalue", requestParam.get("investcyclevalue"));//每月：每月X号 每周：周X，每双周：第n个周X 每十天：第X天
        paramMap.put("investperiods", requestParam.get("investperiods")); //0-每月 1-周 1-双周 指明按月还是按周定投
        paramMap.put("investperiodsvalue", requestParam.get("investperiodsvalue")); // 1-每月 1-每周 2-每双周 每周还是每双周
        paramMap.put("firstinvestdate", requestParam.get("firstinvestdate"));
        paramMap.put("fundcode", requestParam.get("fundcode"));
        paramMap.put("depositacct", requestParam.get("depositacct"));
        paramMap.put("buyflag", requestParam.get("buyflag"));
        paramMap.put("riskmatching", requestParam.get("riskmatching"));
        paramMap.put("riskwarnflag", requestParam.get("riskwarnflag"));
        paramMap.put("tpasswd", requestParam.get("tpasswd"));
        paramMap.put("tano", requestParam.get("tano"));
        paramMap.put("channelid", requestParam.get("channelid"));
        paramMap.put("firstinvestamount", requestParam.get("firstinvestamount"));
        paramMap.put("investmode", requestParam.get("investmode")); //1-按递增金额扣款,2-按后续投资金额不变
        paramMap.put("continueinvestamount", requestParam.get("continueinvestamount")); //后续投资金额
        paramMap.put("operorg", requestParam.get("operorg"));
        paramMap.put("baseindex", "");
        paramMap.put("buyplanname", "");
        paramMap.put("indextype", "");
        paramMap.put("custmanagerid", "");
        paramMap.put("investtime", "");
        paramMap.put("investtimevalue", "");
        paramMap.put("refermobile", "");
        paramMap.put("referral", "");
        paramMap.put("referralcity", "");
        paramMap.put("referralprov", "");
        paramMap.put("transactorcertno", "");
        paramMap.put("transactorcerttype", "");
        paramMap.put("transactorname", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 signSend 定投签约接口
     *
     * @param moneyaccount     资金账号
     * @param custno           客户号
     * @param appsheetserialno 申请单编号
     * @param buyplanno        定投购买协议号
     */
    public static JSONArray jzFixOpenTradeSignSend(String moneyaccount, String custno, String appsheetserialno, String buyplanno) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("trantype", "signSend"); //signSend(定投签约接口)
        param.put("moneyaccount", moneyaccount);
        param.put("custno", custno);
        param.put("appsheetserialno", appsheetserialno);//申请单编号（ 由定期定额申购开通接口返回）
        param.put("saveplanno", buyplanno);//定投购买协议号
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(param)); //内部无返回结果
    }


    /**
     * 字典 430204 定投终止
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzFixInvestmentEndTrade(Map requestParam) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430204");
        paramMap.put("custno", requestParam.get("custno"));
        paramMap.put("buyplanno", requestParam.get("buyplanno"));
        paramMap.put("depositacct", requestParam.get("depositacct"));
        paramMap.put("tpasswd", requestParam.get("tpasswd"));
        paramMap.put("transactionaccountid", requestParam.get("transactionaccountid"));
        paramMap.put("operorg", requestParam.get("operorg"));
        paramMap.put("branchcode", requestParam.get("branchcode"));
        paramMap.put("channelid", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }


//    ---------------------------------------------金正风险接口相关------------------------------------------------------------------------------------------

    /**
     * 字典 430110 风险测评答卷
     *
     * @param invtp            客户类型
     * @param isspeclpapertype 问卷类型
     */
    public static JSONArray jzRiskAssessmentQuestion(String invtp, String isspeclpapertype) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430110");
        paramMap.put("invtp", invtp);
        paramMap.put("isspeclpapertype", isspeclpapertype);
        paramMap.put("answer", "");
        paramMap.put("custno", "");
        paramMap.put("iscontinue", "");
        paramMap.put("pointList", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430111 风险测评提交
     *
     * @param requestParam 可变请求参数
     */
    public static JSONArray jzRiskAssessmentCommit(Map requestParam) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430111");
        paramMap.put("custno", requestParam.get("custno"));
        paramMap.put("invtp", requestParam.get("invtp"));
        paramMap.put("isspeclpapertype", requestParam.get("isspeclpapertype"));
        paramMap.put("answer", requestParam.get("answer"));
        paramMap.put("iscontinue", requestParam.get("iscontinue"));
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

    /**
     * 字典 430366 客户风险等级查询
     *
     * @param custno           客户号
     * @param isspeclpapertype 问卷类型
     */
    public static JSONArray jzCustomerRiskLevelQuery(String custno, String isspeclpapertype) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430366");
        paramMap.put("custno", custno);
        paramMap.put("isspeclpapertype", isspeclpapertype);
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap));
    }

//    ---------------------------------------------金正适当性管理接口相关------------------------------------------------------------------------------------------

    /**
     * 字典 402613 投资者准入协议签署
     *
     * @param custno  客户号
     * @param telno   电话号码
     * @param tpasswd 交易密码
     */
    public static JSONArray jzInvestorAccessAgreementSigned(String custno, String telno, String tpasswd, boolean isAllData) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "402613");
        paramMap.put("custno", custno);
        paramMap.put("telno", telno);
        paramMap.put("tpasswd", tpasswd);
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(paramMap, isAllData));
    }

    /**
     * 字典 402600 投资者类型转换
     *
     * @param conversionParam 可变请求参数
     */
    public static JSONArray jzInvestorsTypeConversion(Map conversionParam) throws Exception {
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("trantype", "402600");
        requestParam.put("acceptmethod", conversionParam.get("acceptmethod"));
        requestParam.put("custno", conversionParam.get("custno"));
        requestParam.put("operorg", conversionParam.get("operorg"));
        requestParam.put("professionflag", conversionParam.get("professionflag"));
        requestParam.put("professionflag_old", conversionParam.get("professionflag_old"));
        requestParam.put("tpasswd", conversionParam.get("tpasswd"));
        requestParam.put("invtp", "");
        requestParam.put("transactorcertno", "");
        requestParam.put("transactorcerttype", "");
        requestParam.put("transactorname", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(requestParam));
    }

    /**
     * 字典 402601 客户文件资料查询
     *
     * @param custno 客户号
     */
    public static JSONArray jzCustFileInfoQuery(String custno) throws Exception {
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("trantype", "402601");
        requestParam.put("custno", custno);
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(requestParam));
    }

    /**
     * 字典 402604 专业投资者信息录入
     *
     * @param informationInputParam 可变请求参数
     */
    public static JSONArray jzProfessionalInvestorInformationInput(Map informationInputParam) throws Exception {
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("trantype", "402604");
        requestParam.put("acceptmethod", informationInputParam.get("acceptmethod"));
        requestParam.put("custno", informationInputParam.get("custno"));
        requestParam.put("tpasswd", informationInputParam.get("tpasswd"));
        requestParam.put("operorg", informationInputParam.get("operorg"));
        requestParam.put("custtype", "");
        requestParam.put("financialassets", "");
        requestParam.put("finorgtype", "");
        requestParam.put("finorgtype", "");
        requestParam.put("invtp", "");
        requestParam.put("netassets", "");
        requestParam.put("peravgincome", "");
        requestParam.put("peravgincomes", "");
        requestParam.put("perinvestexp", "");
        requestParam.put("perinvesttype", "");
        requestParam.put("perinvestwork", "");
        requestParam.put("professionflag", "");
        requestParam.put("transactorcertno", "");
        requestParam.put("transactorcerttype", "");
        requestParam.put("transactorname", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(requestParam));
    }

    /**
     * 字典 402605 专业投资者信息查询
     *
     * @param custno 客户号
     */
    public static JSONArray jzProfessionalInvestorInfomationQuery(String custno) throws Exception {
        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("trantype", "402605");
        requestParam.put("custno", custno);
        requestParam.put("effectdate", "");
        requestParam.put("operdate", "");
        requestParam.put("professionflag", "");
        return JSONArray.fromObject(JzHttpUtil.webApiRequest(requestParam));
    }
}
