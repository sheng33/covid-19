package com.shengq.covid19.handler;

import com.shengq.covid19.dao.SystemUserLoginDetail;
import com.shengq.covid19.utils.JwtTokenUtils;
import com.shengq.covid19.utils.ResultUtilJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理类
 * @author zwq
 * @date 2020-04-04
 **/
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    JwtTokenUtils jwtTokenUtils;
    /**
     * 登录成功返回结果
     * @author zwq
     * @date 2020/4/4
     * @param request
     * @param response
     * @param authentication
     * @return
     **/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 组装JWT
        SystemUserLoginDetail selfUserEntity =  (SystemUserLoginDetail) authentication.getPrincipal();
        String token = jwtTokenUtils.createAccessToken(selfUserEntity);
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code","200");
        resultData.put("msg", "登录成功");
        resultData.put("token",token);
        ResultUtilJson.responseJson(response,resultData);
    }
}
