package com.jpym.ymfrontgm.util;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.szkingdom.webapi.client.BizExecResult;
import com.szkingdom.webapi.client.WebApiHelper_Json;
import lombok.extern.log4j.Log4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j
public class JzHttpUtil {
    private static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static String ipaddr = "";

    static {
        try {
            ipaddr = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private synchronized static String getHeadID() {
        try {
            //休眠1毫秒确保获取值唯一
            Thread.sleep(1);
            return df.format(new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, String> getBaseParams() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", getHeadID());
        map.put("certvalue", "");
        map.put("source", "100");
        map.put("clientip", ipaddr);
        map.put("mktflag", "");
        map.put("userway", "2"); // 2网上交易
        map.put("version", "V003");
        return map;
    }

    /**
     * 接口入口
     *
     * @param map
     * @return
     */
    public static Object webApiRequest(Map<String, Object> map) throws Exception {
        String busiflag = map.containsKey("busiflag") ? map.get("busiflag").toString() : "";
        map.put("busiflag", busiflag);
        map.putAll(JzHttpUtil.getBaseParams());
        System.out.println("接口请求参数：" + getRequestParams(map));

        BizExecResult result = WebApiHelper_Json.webApiRequest(map);
        System.out.println("接口返回结果：【" + "success:" + result.isSuccess() + ", code:" + result.getErrorCode() + ", msg:" + result.getErrorMessage() + ", data:" + result.getDatasets() + "】");

        if (!result.isSuccess()) {
            throw new YmfrontGmException(MsgConstant.ERROR_CODE, result.getErrorMessage());
        }
        return result.getDatasets();

    }

    public static BizExecResult webApiRequest(Map<String, Object> map, boolean isAllData) throws Exception {

        if (!isAllData)
            webApiRequest(map);
        map.putAll(JzHttpUtil.getBaseParams());
        System.out.println("接口请求参数：" + getRequestParams(map));

        BizExecResult result = WebApiHelper_Json.webApiRequest(map);
        System.out.println("接口返回结果：【" + "success:" + result.isSuccess() + ", code:" + result.getErrorCode() + ", msg:" + result.getErrorMessage() + ", data:" + result.getDatasets() + "】");
        return result;
    }

    private static String getRequestParams(Map<String, Object> map) {
        String result = "";
        StringBuffer params = new StringBuffer("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = "\"" + entry.getValue() + "\"";
            params.append(entry.getKey() + ": " + (obj != null ? obj : "\"\"") + ",");
        }
        result = params.toString();
        if (!result.equals("") && result.length() > 0) {
            result = result.substring(0, result.length() - 1) + "}";
        }
        return result;
    }
}
