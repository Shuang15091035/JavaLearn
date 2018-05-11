package com.jpym.ymfrontgm.controller;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.model.CustomerRiskLevelCommitModel;
import com.jpym.ymfrontgm.model.CustomerRiskLevelQueryModel;
import com.jpym.ymfrontgm.model.RiskLevelQuestionModel;
import com.jpym.ymfrontgm.requestparammodel.CustRiskRequestModel;
import com.jpym.ymfrontgm.service.FieldMapperService;
import com.jpym.ymfrontgm.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustRiskController {

    @Autowired
    private FieldMapperService fieldMapperService;

    /**
     * 字典：430110 风险等级测试查询
     *
     * @param invtp            客户类型 0-机构， 1-个人
     * @param isspeclpapertype 问卷类型 0-公募问卷，1-私募问卷
     */
    @GetMapping( value = "/riskAssessmentQuestion" )
    public Result riskAssessmentQuestion(@RequestParam( "invtp" ) String invtp,
                                         @RequestParam( "isspeclpapertype" ) String isspeclpapertype) throws Exception {
        List<Map<String, Object>> riskLevelQuestion = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("invtp", invtp);
        paramMap.put("isspeclpapertype", isspeclpapertype);
        ParamUtil.paramNullCheck(paramMap);
        JSONArray questionArr = JzInterfaceUtil.jzRiskAssessmentQuestion(invtp, isspeclpapertype).getJSONArray(0);
        riskLevelQuestion = fieldMapperService.getNewResults(questionArr, RiskLevelQuestionModel.class);
        // TODO: 2017/12/25 风险等级测试查询 完成待测试

        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "风险测评题目成功", riskLevelQuestion);
    }

    /**
     * 字典: 430111 风险等级测试
     * <p>
     * custno           客户号
     * invtp            投资人类型 0-机构，1-个人
     * isspeclpapertype 问卷类型 0-公募问卷，1-私募问卷
     * answer           题目答案串
     * pointList        分数串
     */
    @PostMapping( value = "/riskAssessmentCommit" )
    public Result riskAssessmentCommit(@RequestBody CustRiskRequestModel riskRequestModel) throws Exception {
        Map requestParam = ParamUtil.transBean2Map(riskRequestModel);
        ParamUtil.requestParameterDicCheck(requestParam);
        Map<String, Object> customerRiskLevelCommit = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("trantype", "430111");
        paramMap.put("custno", riskRequestModel.getCustno());
        paramMap.put("invtp", riskRequestModel.getInvtp());
        paramMap.put("isspeclpapertype", riskRequestModel.getIsspeclpapertype());
        paramMap.put("answer", riskRequestModel.getAnswer());
        ParamUtil.paramNullCheck(paramMap);
        List<RiskLevelQuestionModel> questiones = fieldMapperService.getNewResults((List) riskAssessmentQuestion(riskRequestModel.getInvtp(), riskRequestModel.getIsspeclpapertype()).getData(), RiskLevelQuestionModel.class);
        if (questiones != null) {
            List groupList = ResultUtil.packetAccordingToName("questioncode", questiones);
            String pointList = StringUtil.replaceStringByMap(riskRequestModel.getAnswer(), groupList);
            paramMap.put("pointList", pointList);
            paramMap.put("papercode", JSONObject.fromObject(questiones.get(0)).getString("papercode"));
        }
        paramMap.put("iscontinue", "1");
        Object jzBody = JzHttpUtil.webApiRequest(paramMap);
        JSONArray firstArr = JSONArray.fromObject(jzBody).getJSONArray(0);
        if (firstArr.size() != 0) {
            JSONObject resultObj = firstArr.getJSONObject(0);
            customerRiskLevelCommit = fieldMapperService.getNewResults(resultObj, CustomerRiskLevelCommitModel.class);
        }
        // TODO: 2017/12/25 风险等级测试 完成待检验

        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "风险测评提交成功", customerRiskLevelCommit);
    }

    /**
     * 字典: 430366 网上客户风险查询
     *
     * @param custno           客户号
     * @param isspeclpapertype 问卷类型 0-公募问卷，1-私募问卷
     */
    @GetMapping( value = "/customerRiskLevelQuery" )
    public Result customerRiskLevelQuery(@RequestParam( "custno" ) String custno,
                                         @RequestParam( "isspeclpapertype" ) String isspeclpapertype) throws Exception {
        Map<String, Object> customerRiskLevel = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("custno", custno);
        paramMap.put("isspeclpapertype", isspeclpapertype);
        ParamUtil.paramNullCheck(paramMap);
        JSONArray riskLevel = JzInterfaceUtil.jzCustomerRiskLevelQuery(custno, isspeclpapertype).getJSONArray(0);
        if (riskLevel.size() == 0)
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "查询出错，请稍后查询！");
//        1. 当前风险等级金正根据手机号进行匹配，可能会获取到多个风险等级的值，包括，机构，个人，
        customerRiskLevel = fieldMapperService.getNewResults(riskLevel.getJSONObject(0), CustomerRiskLevelQueryModel.class);
        return ResultUtil.success(MsgConstant.SUCCESS_CODE, "风险测评查询成功", customerRiskLevel);
        // TODO: 2017/12/22 客户风险等级查询 完成待检验
    }
}
