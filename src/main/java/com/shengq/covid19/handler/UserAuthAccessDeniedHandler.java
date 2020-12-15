package com.shengq.covid19.handler;

import com.shengq.covid19.utils.ResultUtilJson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 暂无权限处理类
 * @author zwq
 * @date 2020-04-04
 **/
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 暂无权限返回结果
     * @author zwq
     * @date 2020/4/4
     * @param request
     * @param response
     * @param exception
     * @return
     **/
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception){
        ResultUtilJson.responseJson(response,ResultUtilJson.resultCode(403,"未授权"));
    }
}
