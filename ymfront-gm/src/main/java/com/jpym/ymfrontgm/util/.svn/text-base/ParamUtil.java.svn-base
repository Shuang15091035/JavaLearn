package com.jpym.ymfrontgm.util;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;


public class ParamUtil {

    public static void paramNullCheck(Map<String, Object> paramMap) throws Exception {
        StringBuffer sb = new StringBuffer();

        for (String key : paramMap.keySet()) {
            String value = (String) paramMap.get(key);
            if (value.trim().equals("") || value == null) {
                sb.append("【" + key + "】" + MsgConstant.ERROR_MSG_PARAMATER_NULL + " ");
            }
        }

        // 空值校验
        if (sb.toString().length() > 0) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, sb.toString());
        }


        // XXX校验
    }

    /**
     * 430301 取数据字典
     *
     * @param dictitem
     * @return
     */
    public static List<Map<String, Object>> querydict(String dictitem) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        map.put("trantype", "430301");
        map.put("dictitem", dictitem);
        Object jzBody = JzHttpUtil.webApiRequest(map);
        JSONArray jsonArr = JSONArray.fromObject(jzBody);
        JSONArray GrpBody = jsonArr.getJSONArray(0);
        if (!GrpBody.isEmpty()) {
            for (int i = 0; i < GrpBody.size(); i++) {
                Map<String, Object> bodyMap = StringUtil.jsonToMap(GrpBody.get(i));
                list.add(bodyMap);
            }
        }
        return list;
    }

    public static Double getMaxValueThreeObj(String value1, String value2, String value3) throws Exception {

        Double v1Int = StringUtil.notEmpty(value1) ? Double.parseDouble(value1) : 0;
        Double v2Int = StringUtil.notEmpty(value2) ? Double.parseDouble(value2) : 0;
        Double v3Int = StringUtil.notEmpty(value3) ? Double.parseDouble(value3) : 0;
        Double max = (v1Int > v2Int) ? v1Int : v2Int;
        max = (max > v3Int) ? max : v3Int;
        return max;
    }

    /**
     * 针对GET方法参数字典值校验
     */
    public static boolean requestParameterDicCheck(Map requestMap) throws Exception {
        Set<String> intersection = new HashSet(); //交集
        intersection.clear();
        intersection.addAll(MsgConstant.paramDictionary.keySet());
        intersection.retainAll(requestMap.keySet());
        for (String paramName : intersection) {
            String paramValue = requestMap.get(paramName).toString();
            if (StringUtil.notEmpty(paramValue)) {
                Class enumClass = MsgConstant.paramDictionary.get(paramName);
                Method method = enumClass.getMethod("enumValueExistOfCurrent", String.class);
                Object result = method.invoke(null, paramValue);
                if (!(boolean) result) {
                    throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[" + paramName + "]" + "输入正确的字典值");
                }
            }
        }
        return true;
    }

    /**
     * 针对POST方法参数字典值校验
     * POST方法导致请求体被拦截
     * if (request.getMethod().equals("POST")){
     * BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
     * JSONObject name = JSONObject.fromObject(IOUtils.read(reader));
     * }
     */
    public static boolean requestParameterDicCheck(HttpServletRequest request) throws Exception {
        Set<String> intersection = new HashSet(); //交集
        intersection.clear();
        intersection.addAll(MsgConstant.paramDictionary.keySet());
        intersection.retainAll(request.getParameterMap().keySet());
        for (String paramName : intersection) {
            String paramValue = request.getParameter(paramName);
            if (StringUtil.notEmpty(paramValue)) {
                Class enumClass = MsgConstant.paramDictionary.get(paramName);
                Method method = enumClass.getMethod("enumValueExistOfCurrent", String.class);
                Object result = method.invoke(null, paramValue);
                if (!(boolean) result) {
                    throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[" + paramName + "]" + "输入正确的字典值");
                }
            }
        }
        return true;
    }

    /**
     * 请求的签名校验
     *
     * @param request 请求体
     * @return 签名是否符合要求
     */
    public static boolean requestHeadParamSignCheck(HttpServletRequest request) throws Exception {
        //       获取签名参数 + 签名
        String appKey = request.getHeader("appKey");
        String contentlength = request.getHeader("contentlength");
        String httpmethod = request.getHeader("httpmethod");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        // 签名参数 + 签名 空值校验
        Map<String, Object> signMap = new HashMap<>();
        signMap.put("appKey", appKey);
        signMap.put("contentlength", contentlength);
        signMap.put("httpmethod", httpmethod);
        signMap.put("timestamp", timestamp);
        signMap.put("sign", sign);
        try {
            ParamUtil.paramNullCheck(signMap);
        } catch (Exception e) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "签名值为空");
        }
        return SignUtil.signIstrue(appKey, timestamp, contentlength, httpmethod, sign);
    }

    public static boolean requestParameterFormateCheck(String paramValue) throws Exception {
        try {
            MsgConstant.format.parse(paramValue);
        } catch (Exception e) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[" + paramValue + "]" + MsgConstant.format);
        }
        return true;
    }

    /**
     * javaBean转化为Map结果
     *
     * @param bean 带转化的bean对象
     */
    public static Map<String, Object> transBean2Map(Object bean) {

        if (bean == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(bean);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }

//        //businesscode:业务代码：22申购；24赎回；36转换；59定投协议开通；60定投协议撤销; 61:定投协议变更
//        List<Map<String, Object>> dictList = ParamUtil.querydict("130010");
//        //paytype:支付方式：1银行卡转账 2银行卡汇款 3货币转换方式
//        List<Map<String, Object>> dictList2 = ParamUtil.querydict("110229");
//        //状态：委托状态定义：00待复核；01待勾兑；02待报；04废单；05已撤；06已报；07已确认；08已结束
//        List<Map<String, Object>> dictList3 = ParamUtil.querydict("110003");
//        //支付状态：00未支付；01委托正在处理；02支付成功；03支付失败；07托收成功
//        List<Map<String, Object>> dictList4 = ParamUtil.querydict("110066");
//        //支付方式
//        List<Map<String, Object>> dictList6 = ParamUtil.querydict("110229");
//        //分红方式
//        List<Map<String, Object>> dictList5 = ParamUtil.querydict("110001");

}
