package com.wps.studyplatform.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpInterceptor implements HandlerInterceptor{

    @Value("${pass.value}")
    private boolean isPass;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (isPass){
            System.out.println(isPass);
            return true;
        }else {
            System.out.println(isPass);
            return false;
        }

    }
}
