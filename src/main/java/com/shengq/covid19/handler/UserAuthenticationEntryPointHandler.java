package com.shengq.covid19.handler;

import com.shengq.covid19.utils.ResultUtilJson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户未登录处理类
 * @author zwq
 * @date 2020-04-04
 **/
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    /**
     * 用户未登录返回结果
     * @author zwq
     * @date 2020/4/4
     * @param request
     * @param response
     * @param exception
     * @return
     **/
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
        ResultUtilJson.responseJson(response,ResultUtilJson.resultCode(401,"未登录"));
    }
}
