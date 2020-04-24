package com.wps.springsecurity.security.handle;

import com.alibaba.fastjson.JSON;
import com.wps.springsecurity.constant.HttpStatus;
import com.wps.springsecurity.utils.ApiResult;
import com.wps.springsecurity.utils.ServletUtils;
import com.wps.springsecurity.utils.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


/**
 * @Title AuthenticationEntryPointImpl
 * @Description 认证失败处理类 返回未授权
 *                用户处于无登录状态或token过期时，访问资源时会提示此异常。
 * @auther wps
 * @Date 2020/4/249:50
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(ApiResult.fail(String.valueOf(code), msg)));
    }
}
