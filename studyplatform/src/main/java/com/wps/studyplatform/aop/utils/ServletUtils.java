package com.wps.studyplatform.aop.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 客户端工具类
 */
public class ServletUtils {
    /**
     * 获得String参数
     */
    public static String getParameter(String name){
        return getRequest().getParameter(name);
    }
    /**
     * 获取Integer参数
     */
     public static Integer getParameterToInt(String name){
         return Integer.valueOf(getRequest().getParameter(name));
     }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest(){
        return getRequestAttributes().getRequest();
    }

    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes)attributes;
    }
    /**
     * 获取response
     */
    public static HttpServletResponse getResponse(){
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession(){
        return getRequest().getSession();
    }
    /**
     * 将字符串渲染到客户端
     *
     */
    public static String renderString(HttpServletResponse response,String string){
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
