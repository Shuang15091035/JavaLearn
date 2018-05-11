package com.jpym.ymfrontgm.controller;

import com.jpym.ymfrontgm.util.*;
import com.szkingdom.kwis.common.security.DESEncrypt;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JzTestController {

    @PostMapping( value = "/queryProducts" )
    public Result queryProducts() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430306");
        Object obj = JzHttpUtil.webApiRequest(paramMap);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, MsgConstant.SUCCESS_MEG_PRODUCT_DETAIL_QUERY, obj);
    }

    /**
     * 字典 430311 查询可撤单交易申请
     * 在途交易内部
     *
     * @param custno 客户号
     */
    @GetMapping( value = "queryCancleOrderList" )
    public Result queryCancleOrderList(@RequestParam( "custno" ) String custno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430311");
        paramMap.put("custno", custno);
        ParamUtil.paramNullCheck(paramMap);
        Object jzBody = JzHttpUtil.webApiRequest(paramMap);
        // TODO: 2017/12/26  撤单列表 内部测试使用
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "可撤单列表查询成功", jzBody);
    }

    /**
     * 字典 430404 查询客户经理信息
     */
    @GetMapping( value = "queryCustManagerInfo" )
    public Result queryCustManagerInfo() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430404");
        ParamUtil.paramNullCheck(paramMap);
        Object jzBody = JzHttpUtil.webApiRequest(paramMap);
        // TODO: 2018/2/1 查询客户经理信息 内部测试
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "查询客户经理信息", jzBody);
    }

    /**
     * 字典 430330 查询所有网上支付支付渠道/支付网点
     */
    @GetMapping( value = "/queryNetworkTradeChannelidAndPaycenterid" )
    public Result queryNetworkTradeChannelidAndPaycenterid() throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430330");
        paramMap.put("specialflag", "1");
        ParamUtil.paramNullCheck(paramMap);
        Object jzBody = JzHttpUtil.webApiRequest(paramMap);
        // TODO: 2018/1/3 内部测试使用，查询所有网上支付支付渠道/支付网点
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "查询产品持仓详情成功", jzBody);
    }

    /**
     * 字典 490024 客户密码重置 内部使用
     *
     * @param newPwd 新密码
     */
    @PostMapping( value = "/resetTradePassword" )
    public Result modifyTradePassword(@RequestParam( "certificateno" ) String certificateno,
                                      @RequestParam( "certificatetype" ) String certificatetype,
                                      @RequestParam( "newPwd" ) String newPwd,
                                      @RequestParam( "pwdtype" ) String pwdtype) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "490024");
        paramMap.put("accttype", "0");
        paramMap.put("certificateno", certificateno);
        paramMap.put("certificatetype", certificatetype);
        paramMap.put("newpwd", newPwd);
        paramMap.put("pwdtype", pwdtype);// 0交易密码 1登录密码
        ParamUtil.paramNullCheck(paramMap);
        paramMap.put("newpwd", DESEncrypt.strEnc(newPwd));
        Object jzBody = JzHttpUtil.webApiRequest(paramMap);
        // TODO: 2018/2/1 客户密码重置 内部测试使用
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "修改交易密码成功", jzBody);
    }

    /**
     * 字典：430112 风险等级查询 内部测试使用
     *
     * @param invtp 投资人类别 0-机构，1-个人
     *              此接口用于客户风险查询时，根据answerpoint字段，获取到对应的等级的风险值
     */
    @GetMapping( value = "/riskLevelQuery" )
    private JSONArray riskLevelQuery(@RequestParam( "invtp" ) String invtp) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430112");
        paramMap.put("invtp", invtp);

        Object jzBody = JzHttpUtil.webApiRequest(paramMap);
        JSONArray firstArr = JSONArray.fromObject(jzBody).getJSONArray(0); //测评的所有风险等级
        // TODO: 2018/2/1 风险等级值来源的表对应
        return firstArr;
    }

    /**
     * 字典：430307 查询基金详情
     *
     * @param fundcode 基金代码
     * @param tano     TA编号
     */
    @GetMapping( value = "QueryFundDetail" )
    public JSONArray QueryFundDetail(@RequestParam( value = "fundcode", required = true ) String fundcode,
                                     @RequestParam( value = "tano", required = true ) String tano) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430307");
        paramMap.put("fundcode", fundcode);
        paramMap.put("tano", tano);

        Object jzBody = JzHttpUtil.webApiRequest(paramMap);
        JSONArray firstArr = JSONArray.fromObject(jzBody); //测评的所有风险等级
        return firstArr;
    }

    /**
     * 字典：430424 查询基金历史净值
     *
     * @param begindate 开始日期
     * @param enddate   结束日期
     * @param fundcode  基金代码
     * @param tano      TA编号
     */
    @GetMapping( value = "JzQueryFundHistroyNav" )
    public JSONArray JzQueryFundHistroyNav(@RequestParam( value = "begindate", required = true ) String begindate,
                                           @RequestParam( value = "enddate", required = true ) String enddate,
                                           @RequestParam( value = "fundcode", required = true ) String fundcode,
                                           @RequestParam( value = "tano", required = true ) String tano) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430424");
        paramMap.put("begindate", tano);
        paramMap.put("enddate", tano);
        paramMap.put("fundcode", fundcode);
        paramMap.put("tano", tano);

        Object jzBody = JzHttpUtil.webApiRequest(paramMap);
        JSONArray firstArr = JSONArray.fromObject(jzBody); //测评的所有风险等级
        return firstArr;
    }

    @GetMapping( value = "jZBusinessCode" )
    public List jZBusinessCode(String dicParam) throws Exception {

        return ParamUtil.querydict(dicParam);
    }


    /**
     * 字典: 430303 取客户信息
     * 内部测试使用
     *
     * @param custno 客户号
     */
    @GetMapping( value = "/queryCustInfo" )
    public Result queryCustInfo(@RequestParam( "custno" ) String custno) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", custno);
        ParamUtil.paramNullCheck(paramMap);
        // TODO: 2017/12/26 客户信息获取
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "查客户信息成功", JzInterfaceUtil.jzQueryCustInfo(custno));
    }

    @GetMapping("queryCustAsset")
    public Object queryCustAsset(String custno) throws Exception{
        return  JzInterfaceUtil.jzQueryCustAssetIncome(custno);
    }

    @GetMapping("everyDayIncomeDetail")
    public Object everyDayIncomeDetail (String custno) throws Exception{
        return JzInterfaceUtil.jzQueryEveryDayIncomeDetail(custno);
    }


}