package com.wps.studyplatform.utils;

import com.wps.studyplatform.exception.base.BaseException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title SysUtils
 * @Description 项目中公用方法
 * @auther wps
 * @Date 2020/4/1619:29
 */
@Component
public class SysUtils {

    //判断请求中，角色是否是管理员
    public boolean isAdmin(HttpServletRequest request){
        Claims claims =(Claims) request.getAttribute("claims");
        if(null!=claims){
            if(claims.get("roles").toString().contains("admin")){
                return true;
            }else {
                return  false;
            }
        }else {
            throw new BaseException("当前用户没有登录");
        }

    }
}
