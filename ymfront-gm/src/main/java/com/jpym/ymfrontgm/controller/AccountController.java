package com.jpym.ymfrontgm.controller;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.model.AddBankCardModel;
import com.jpym.ymfrontgm.model.BankInfoModel;
import com.jpym.ymfrontgm.model.OpenAccountModel;
import com.jpym.ymfrontgm.requestparammodel.AddBankCardRequestModel;
import com.jpym.ymfrontgm.requestparammodel.BgMsgCheckRequestModel;
import com.jpym.ymfrontgm.requestparammodel.BgMsgSendRequestModel;
import com.jpym.ymfrontgm.requestparammodel.OpenAccountRequestModel;
import com.jpym.ymfrontgm.service.AccountService;
import com.jpym.ymfrontgm.service.BankInfoService;
import com.jpym.ymfrontgm.service.FieldMapperService;
import com.jpym.ymfrontgm.util.*;
import com.szkingdom.kwis.common.security.DESEncrypt;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private FieldMapperService fieldMapperService;
    @Autowired
    private BankInfoService bankInfoService;
    @Autowired
    private AccountService accountService;

    /**
     * 字典：430100 客户开户
     * <p>
     * invtp            投资人类别
     * custname         客户姓名
     * certificateno    证件号码
     * certificatetype  证件类型 0-身份证
     * channelid        银行名称
     * depositacct      银行卡号
     * authenticateflag 鉴权标志
     * mobileno         手机号码
     * tpasswd          交易密码
     */
    @PostMapping( value = "/customerOpenAccount" )
    public Result customerOpenAccount(@RequestBody OpenAccountRequestModel requestModel) throws Exception {

        Map requestParam = ParamUtil.transBean2Map(requestModel);
        ParamUtil.requestParameterDicCheck(requestParam);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430100");
        paramMap.put("invtp", requestModel.getInvtp()); //客户类型 0-机构，1-个人 ,目前版本只支持传入1
        paramMap.put("custname", requestModel.getCustname());
        paramMap.put("certificateno", requestModel.getCertificateno());
        paramMap.put("certificatetype", requestModel.getCertificatetype()); //目前只支持证件类型为身份证
        paramMap.put("sex", requestModel.getSex()); //[110059]0-女 1-男
        paramMap.put("country", requestModel.getCountry()); //[110049] 156-中国
        paramMap.put("address", requestModel.getAddress()); //住址
        paramMap.put("vailddate", requestModel.getVailddate()); // 证件有效期
        paramMap.put("vocationcode", requestModel.getVocationcode()); // [100011]投资者职业代码
        paramMap.put("channelid", requestModel.getChannelid()); //传入的值为channelid
        paramMap.put("depositacct", requestModel.getDepositacct());
        paramMap.put("authenticateflag", requestModel.getAuthenticateflag()); //鉴权标志只能传1
        paramMap.put("mobileno", requestModel.getMobileno());
        paramMap.put("tpasswd", requestModel.getTpasswd());
        paramMap.put("verificationcode", requestModel.getVerificationcode());
        ParamUtil.paramNullCheck(paramMap);
        paramMap.remove("verificationcode", requestModel.getVerificationcode());
        paramMap.put("tpasswd", DESEncrypt.strEnc(requestModel.getTpasswd()));
//        1.获取本地数据库配置channelid，channelname，bankname
        BankInfoModel bankInfo = bankInfoService.channelidAndChannelNameByBankName(requestModel.getChannelid());
        paramMap.put("channelid", bankInfo.getChannelid());
        paramMap.put("channelname", bankInfo.getChannelname());
        paramMap.put("bankname", bankInfo.getBankname());
        paramMap.put("custfullname", requestModel.getCustname());
        paramMap.put("depositacctname", requestModel.getCustname()); //银行账户名，一般是客户姓名
        paramMap.put("depositname", requestModel.getCustname());
        paramMap.put("authenticateflag", "1"); //0-未鉴权，1-已鉴权
        paramMap.put("operorg", "9999");
        paramMap.put("tradingmethod", "0,2"); //2-网上交易
//        2.短信鉴权确认,保证验证码填写成功
//        Map<String ,Object> msgCheckParam = new HashMap<>();
//        msgCheckParam.put("certificatetype", requestModel.getCertificatetype());
//        msgCheckParam.put("certificateno", requestModel.getCertificateno());
//        msgCheckParam.put("depositacctname", requestModel.getCustname());
//        msgCheckParam.put("channelid", requestModel.getChannelid()); //传入的值为channelid
//        msgCheckParam.put("depositacct", requestModel.getDepositacct());
//        msgCheckParam.put("mobiletelno", requestModel.getMobileno());
//        msgCheckParam.put("verificationCode", requestModel.getVerificationcode());
//        JSONArray msgCheck = JzInterfaceUtil.jzBgMsgCheck(msgCheckParam).getJSONArray(0);
//        if (msgCheck.size() == 0 || !msgCheck.getJSONObject(0).getString("code").equals("0000"))
//            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "短信验证码填写错误！");
//        3.调用金正开户接口
        JSONArray openInfo = JzInterfaceUtil.jzCustomerOpenAccount(paramMap).getJSONArray(0);
        if (openInfo.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "接口异常！请稍后开户");
        JSONObject resultObj = openInfo.getJSONObject(0);
//        4.调用字段映射Service
        Map<String, Object> openAccountInfo = fieldMapperService.getNewResults(resultObj, OpenAccountModel.class);
//        5.调用适当性协议签署接口
        accountService.custAdapterAgreementSignedCheck(openAccountInfo.get("custno").toString());
        // TODO: 2017/12/25 客户开户 完成待测试
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "客户开户成功", openAccountInfo);
    }

    /**
     * 字典: 430104 增卡
     *
     * @ custno          客户号
     * @ certificatetype 证件类型
     * @ certificateno   证件号
     * @ depositacctname 客户姓名
     * @ channelid       网点代码
     * @ depositacct     银行账号
     * @ mobile          手机号
     * @ tpasswd         交易密码
     */
    @PostMapping( value = "/addBankCard" )
    public Result addBankCard(@RequestBody AddBankCardRequestModel requestModel) throws Exception {
        Map requestParam = ParamUtil.transBean2Map(requestModel);
        ParamUtil.requestParameterDicCheck(requestParam);
        Map<String, Object> successResult = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430104");
        paramMap.put("custno", requestModel.getCustno());
        paramMap.put("certificatetype", requestModel.getCertificatetype());
        paramMap.put("certificateno", requestModel.getCertificateno());
        paramMap.put("depositacctname", requestModel.getDepositacctname());
        paramMap.put("channelid", requestModel.getChannelid());
        paramMap.put("depositacct", requestModel.getDepositacct());
        paramMap.put("mobiletelno", requestModel.getMobileno());
        paramMap.put("tpasswd", requestModel.getTpasswd()); //只有柜台设置了增卡不需要校验密码的话，才能传空值
        paramMap.put("verificationcode", requestModel.getVerificationcode());
        ParamUtil.paramNullCheck(paramMap);
        paramMap.remove("verificationcode", requestModel.getVerificationcode());
        paramMap.put("tpasswd", DESEncrypt.strEnc(requestModel.getTpasswd()));
//        1.获取本地数据库配置channelid，channelname，bankname
        BankInfoModel bankInfo = bankInfoService.channelidAndChannelNameByBankName(requestModel.getChannelid());
        paramMap.put("channelid", bankInfo.getChannelid());
        paramMap.put("channelname", bankInfo.getChannelname());
        paramMap.put("bankname", bankInfo.getBankname());
        paramMap.put("authenticateflag", "1"); //0-未鉴权，1-已鉴权
        paramMap.put("operorg", "9999");
//        2.调用短信鉴权确认接口,保证验证码填写成功
        Map<String, Object> msgCheckParam = new HashMap<>();
        msgCheckParam.put("certificatetype", requestModel.getCertificatetype());
        msgCheckParam.put("certificateno", requestModel.getCertificateno());
        msgCheckParam.put("depositacctname", requestModel.getDepositacctname());
        msgCheckParam.put("channelid", requestModel.getChannelid()); //传入的值为channelid
        msgCheckParam.put("depositacct", requestModel.getDepositacct());
        msgCheckParam.put("mobiletelno", requestModel.getMobileno());
        msgCheckParam.put("verificationCode", requestModel.getVerificationcode());
        JSONArray msgCheck = JzInterfaceUtil.jzBgMsgCheck(msgCheckParam).getJSONArray(0);
        if (msgCheck.size() == 0 || !msgCheck.getJSONObject(0).getString("code").equals("0000"))
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "请填写正确的短信验证码！");
//        3.调用添加银行卡接口
        JSONArray addBankInfo = JzInterfaceUtil.jzAddBankCard(paramMap).getJSONArray(0);
        if (addBankInfo.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "增卡失败，请稍后添加银行卡！");
        JSONObject resultObj = addBankInfo.getJSONObject(0);
//        4.调用字段映射Service
        successResult = fieldMapperService.getNewResults(resultObj, AddBankCardModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "银行卡添加成功", successResult);
        // TODO: 2017/12/25 添加银行卡 完成待测试
    }

    /**
     * 字典 bgMsgSend 短信鉴权发送
     *
     * @ depositacctname 客户姓名
     * @ certificatetype 证件类型
     * @ certificateno   证件号码
     * @ channelid       银行名称
     * @ depositacct     银行卡号
     * @ mobileno        手机号码
     */
    @PostMapping( value = "/bgMsgSend" )
    public Result bgMsgSend(@RequestBody BgMsgSendRequestModel requestModel) throws Exception {
        Map requestParam = ParamUtil.transBean2Map(requestModel);
        ParamUtil.requestParameterDicCheck(requestParam);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("depositacctname", requestModel.getDepositacctname());
        paramMap.put("certificatetype", requestModel.getCertificatetype());
        paramMap.put("certificateno", requestModel.getCertificateno());
        paramMap.put("channelid", requestModel.getChannelid()); //传入的值为channelid
        paramMap.put("depositacct", requestModel.getDepositacct());
        paramMap.put("mobiletelno", requestModel.getMobileno());
        ParamUtil.paramNullCheck(paramMap);
        paramMap.put("subbankno", requestModel.getChannelid()); //这里填写的字段值为 channelid
        if (JzInterfaceUtil.jzBgMsgSend(paramMap).getJSONArray(0).size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "接口异常，稍后点击验证码发送！");
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "银行卡鉴权短信发送成功");
        // TODO: 2017/12/25 短信鉴权发送完成 待检验
    }

    /**
     * bgMsgCheck 短信鉴权确认(验证码校验)
     *
     * @ certificatetype  证件类型
     * @ certificateno    证件号码
     * @ depositacctname  银行名称
     * @ channelid        网点代码
     * @ depositacct      银行账号
     * @ mobiletelno      手机号码
     * @ verificationcode 手机验证码
     */
    @PostMapping( value = "/bgMsgCheck" )
    public Result bgMsgCheck(@RequestBody BgMsgCheckRequestModel requestModel) throws Exception {
        Map requestParam = ParamUtil.transBean2Map(requestModel);
        ParamUtil.requestParameterDicCheck(requestParam);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("certificatetype", requestModel.getCertificatetype());
        paramMap.put("certificateno", requestModel.getCertificateno());
        paramMap.put("depositacctname", requestModel.getDepositacctname());
        paramMap.put("channelid", requestModel.getChannelid()); //传入的值为channelid
        paramMap.put("depositacct", requestModel.getDepositacct());
        paramMap.put("mobiletelno", requestModel.getMobileno());
        paramMap.put("verificationCode", requestModel.getVerificationcode());
        ParamUtil.paramNullCheck(paramMap);
        JSONArray msgCheck = JzInterfaceUtil.jzBgMsgCheck(paramMap).getJSONArray(0);
        if (msgCheck.size() == 0 || !msgCheck.getJSONObject(0).getString("code").equals("0000"))
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "短信签约失败，请填写正确验证码！");
        // TODO: 2017/12/25 短信鉴权确认 完成待测试
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "银行卡鉴权验证码校验成功");
    }

    /**
     * 字典：430305 查询基金资产 （公募持仓）
     *
     * @param custno 客户号
     */
    @GetMapping( value = "/assetAllocation" )
    public Result assetAllocation(@RequestParam( "custno" ) String custno,
                                  @RequestParam( value = "pageindex", defaultValue = "1" ) Number pageindex,
                                  @RequestParam( value = "pagesize", defaultValue = MsgConstant.PAGE_SIZE ) Number pagesize) throws Exception {
        Map<String, Object> assetAllocation = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", custno);
        ParamUtil.paramNullCheck(paramMap);
        String flag = "9"; //Flag == 0 查询货币基金资产信息；Flag == 2 查询非货币基金资产信息；Flag == 9 你要想全部查.
        assetAllocation = accountService.assetAllocation(custno, flag, pageindex, pagesize);
        // TODO: 2017/12/25 公募持仓 完成待测试
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "公募持仓成功", assetAllocation);
    }

    /**
     * 以基金代码为维度的资产详情
     *
     * @param custno 客户号
     */
    @GetMapping( value = "/productAssetAllocationDetail" )
    public Result productAssetAllocationDetail(@RequestParam( "custno" ) String custno,
                                               @RequestParam( "fundcode" ) String fundcode) throws Exception {
        Map<String, Object> fundAsset = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", custno);
        paramMap.put("fundcode", fundcode);
        ParamUtil.paramNullCheck(paramMap);
        String flag = "9"; //Flag == 0 )//查询货币基金资产信息,Flag == 2 )//查询非货币基金资产信息,你要想全部查，可以入一个9.
        // TODO: 2017/12/25 产品资产详情 完成待测试
        fundAsset = accountService.productAssetAllocationDetail(custno, fundcode, flag);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "持仓详情接口成功", fundAsset);
    }
}