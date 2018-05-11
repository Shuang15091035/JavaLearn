package com.jpym.ymfrontgm.interceptor;

import com.jpym.ymfrontgm.util.ParamUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ApiInterceptor extends HandlerInterceptorAdapter {
    /**
     * 在控制器之前拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

//        请求签名校验
        ParamUtil.requestHeadParamSignCheck(request);

//        请求字典的校验
        if (request.getMethod().equals("GET")) {
            ParamUtil.requestParameterDicCheck(request);
        }
        return true;
    }
}
