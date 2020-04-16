package com.wps.studyplatform.jwt.intercept;

import com.wps.studyplatform.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title JWTIntercept
 * @Description JWT拦截器
 * @auther wps
 * @Date 2020/4/1615:50
 */
/**
 * Spring为我们提供了 org.springframework.web.servlet.handler.HandlerInterceptorAdapter这个适配器，
 * 继承此类，可以非常方便的实现自己的拦截器。他有三个方法：
 * 分别实现预处理、后处理（调用了Service并返回ModelAndView，但未进行页面渲* 染）、返回处理（已经渲染了页面）
 * 在preHandle中，可以进行编码、安全控制等处理；
 * 在postHandle中，有机会修改ModelAndView；
 * 在afterCompletion中，可以根据ex是否为null判断是否发生了异常，进行日志记录。
 */
@Component
public class JWTIntercept extends HandlerInterceptorAdapter {

    @Autowired
    private JWTUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("您被拦截了");
        String authHeader=request.getHeader("Authorization");
        if(null!=authHeader&& authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            Claims claims=jwtUtil.parseJWT(token);
            if (null!=claims){
                request.setAttribute("claims",claims);
            }
        }

        return true;
    }
}
