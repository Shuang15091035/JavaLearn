package com.jpym.ymfrontgm.interceptor;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.util.MsgConstant;
import com.jpym.ymfrontgm.util.StringUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ThirdDataInterceptor extends HandlerInterceptorAdapter {


    private static Set<String> queryParamDictionary = new HashSet<>(Arrays.asList("fundcode", "startdate", "enddate"));
    private Set<String> intersection = new HashSet<String>(); //交集

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        Set<String> requestParam = httpServletRequest.getParameterMap().keySet();
        intersection.clear();
        intersection.addAll(queryParamDictionary);
        intersection.retainAll(requestParam);
        for (String paramName : intersection) {
            String paramValue = httpServletRequest.getParameter(paramName);
            switch (paramName) {
                case "fundcode": {

                    if (paramValue.getBytes("utf-8").length > 6) {
                        throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[" + paramName + "]" + "入参数据缓冲区太小");
                    }
                    break;
                }
                case "startdate":
                case "enddate": {
                    if (StringUtil.notEmpty(paramValue)) {
                        if (paramValue.getBytes("utf-8").length > 8) {
                            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[" + paramName + "]" + "入参数据缓冲区太小");
                        }
                        try {
                            MsgConstant.format.parse(paramValue);
                        } catch (Exception e) {
                            throw new YmfrontGmException(MsgConstant.ERROR_CODE, "[" + paramName + "]" + "yyyyMMdd");
                        }
                    }
                    break;
                }
                default:
            }
        }
        return true;
    }
}
