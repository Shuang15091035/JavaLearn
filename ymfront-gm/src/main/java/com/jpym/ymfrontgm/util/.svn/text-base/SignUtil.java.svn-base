package com.jpym.ymfrontgm.util;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import lombok.extern.log4j.Log4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * api签名验证工具类
 * 该签名为私钥签名
 * 1. 用户验证，判断key是否存在，同时查询出对应的secret用于签名验证
 * 2. 时间戳验证，判断请求是否过期
 * 3. 签名验证，根据算法将参数进行签名得到sign与参数中的sign进行对比
 * 签名算法：
 * 3.1 选择签名参数
 * appKey
 * appSecret
 * timestamp(时间戳) 例如：2018-01-08 14:33:20
 * contentlength(数据内容长度)
 * httpmethod(请求方式)  例如："post"
 * 3.2 签名参数排序
 * 3.3 签名参数排序后拼接: 例如 appkey=xxx&appSecret=xxx&contentlength=xxx&httpmethod=xxx&timestamp=xxx
 * 3.4 签名参数进行MD5加密, 得到签名sign
 * <p>
 * 注意：http Header请求头中传appKey, contentlength, httpmethod, timestamp, sign
 */
@Log4j
public class SignUtil {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("jpcf-private-key");

    /**
     * 总入口
     *
     * @param appKey
     * @param timestamp
     * @param contentlength
     * @param httpmethod
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean signIstrue(String appKey, String timestamp, String contentlength, String httpmethod, String sign) throws Exception {

        // 判断appKey是否存在
        SignUtil.appKeyIsExist(appKey);
        // 判断时间戳是否过期
        SignUtil.timestampIsExpired(timestamp);
        // 签名验证
        SignUtil.signCheck(appKey, timestamp, contentlength, httpmethod, sign);
        return true;
    }

    /**
     * 判断appKey是否存在
     *
     * @param appKey
     * @return
     */
    private static void appKeyIsExist(String appKey) throws Exception {
        String sourceAppKey = SignUtil.bundle.getString("appKey");
        if (appKey.equals(sourceAppKey)) {
        } else {
            throw new YmfrontGmException("9999", "签名appKey参数错误！");
        }
    }

    /**
     * 判断时间戳是否过期： 改签名有效期为1分钟
     *
     * @param timestamp
     * @return
     */
    private static void timestampIsExpired(String timestamp) throws Exception {

        //注意format的格式要与日期String的格式相匹配
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long fromDate = sdf.parse(timestamp).getTime();  // 毫秒
        long nowDate = new Date().getTime(); // 毫秒
        int deltSeconds = (int) ((nowDate - fromDate) / (1000 * 60)); // 转化成分钟
        if (deltSeconds <= 1) {
        } else {
            throw new YmfrontGmException("9999", "API签名过期！");
        }
    }

    /**
     * 签名验证: 生成的签名和传过来的签名是否一致
     *
     * @param appKey
     * @param timestamp
     * @param contentlength
     * @param httpmethod
     * @return
     */
    private static void signCheck(String appKey, String timestamp, String contentlength, String httpmethod, String sign) throws Exception {
        String appSecret = SignUtil.bundle.getString("appSecret");
        String sourceSign = getSourceSign(appKey, appSecret, timestamp, contentlength, httpmethod);
        if (sourceSign.equals(sign)) {

        } else {
            throw new YmfrontGmException("9999", "API签名错误！");
        }
    }

    /**
     * 生成签名： sourceSign
     *
     * @param appKey
     * @param timestamp
     * @param contentlength
     * @param httpmethod
     * @return
     */
    private static String getSourceSign(String appKey, String appSecret, String timestamp, String contentlength, String httpmethod) {
        String[] keys = {"appKey", "appSecret", "timestamp", "contentlength", "httpmethod"};

        Map<String, String> keyValueMap = new HashMap<>();
        keyValueMap.put("appKey", appKey);
        keyValueMap.put("appSecret", appSecret);
        keyValueMap.put("timestamp", timestamp);
        keyValueMap.put("contentlength", contentlength);
        keyValueMap.put("httpmethod", httpmethod);

        //key排序
        Arrays.sort(keys);

        //每一个key-value用&分割，得到新的字符串
        String keyAndValueStr = "";
        for (int i = 0, n = keys.length; i < n; i++) {
            keyAndValueStr += keys[i] + "=" + keyValueMap.get(keys[i]) + "&";
        }

        //去掉最后一个&
        String signStr = keyAndValueStr.substring(0, keyAndValueStr.length() - 1);

        System.out.println("【签名字符串】:" + signStr);

        //新的字符串进行md5加密，转化成hex16格式， 得到签名
        return Md5Encrypt.md5(signStr);
    }

    // 测试
    public static void main(String[] args) {
        String appKey = SignUtil.bundle.getString("appKey");
        String appSecret = SignUtil.bundle.getString("appSecret");
        String timestamp = "2018-02-07 14:11:03";
        String contentlength = "1";
        String httpmethod = "get";
        String sign = getSourceSign(appKey, appSecret, timestamp, contentlength, httpmethod);
        log.info("【签名】:" + sign);
    }
}
