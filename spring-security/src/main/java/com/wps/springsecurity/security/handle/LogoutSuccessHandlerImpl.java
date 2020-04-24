package com.wps.springsecurity.security.handle;

import com.alibaba.fastjson.JSON;
import com.wps.springsecurity.constant.HttpStatus;
import com.wps.springsecurity.security.LoginUser;
import com.wps.springsecurity.security.service.TokenService;
import com.wps.springsecurity.utils.ApiResult;
import com.wps.springsecurity.utils.ServletUtils;
import com.wps.springsecurity.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title LogoutSuccessHandlerImpl
 * @Description 自定义退出处理类 返回成功
 * @auther wps
 * @Date 2020/4/2410:59
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getUUID());
            // 记录用户退出日志

        }
        ServletUtils.renderString(response, JSON.toJSONString(ApiResult.fail(String.valueOf(HttpStatus.SUCCESS), "退出成功")));
    }
}
