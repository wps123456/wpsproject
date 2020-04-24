package com.wps.springsecurity.security.service;

/**
 * @Title SysLoginService
 * @Description
 * @auther wps
 * @Date 2020/4/2215:57
 */

import com.wps.springsecurity.constant.SecurityConstant;
import com.wps.springsecurity.exception.SystemLoginException.SystemLoginException;
import com.wps.springsecurity.exception.SystemLoginException.UserPasswordNotMatchException;
import com.wps.springsecurity.exception.base.BaseException;
import com.wps.springsecurity.redis.RedisCache;
import com.wps.springsecurity.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password,String code,String uuid)
    {
        String verifyKey=SecurityConstant.CAPTCHA_CODE_KEY+uuid;
        String cacheCode=redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (null==cacheCode){
            throw new SystemLoginException("验证码已过期，请重新获取验证码");
        }
        if (null==code){
            throw new SystemLoginException("请输入验证码");
        }
        //忽略大小写比较
        if(!code.equalsIgnoreCase(cacheCode)){
            throw new SystemLoginException("验证码输入错误");
        }



        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername(验证登录信息)
            Authentication auth=  new UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticationManager.authenticate(auth);
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                throw new UserPasswordNotMatchException("用户名或密码错误");
            }
            else
            {

                throw new BaseException(e.getMessage());
            }

        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
