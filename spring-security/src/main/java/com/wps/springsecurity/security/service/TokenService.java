package com.wps.springsecurity.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wps.springsecurity.security.LoginUser;
import com.wps.springsecurity.testcontroller.entity.SysUser;
import com.wps.springsecurity.testcontroller.mapper.SysUserMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title TokenService
 * @Description
 * @auther wps
 * @Date 2020/4/2214:49
 */
@Component
public class TokenService
{
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;
    @Value("${token.key}")
    private String key ;
    @Value("${token.expire-time}")
    private int expire ;//一个小时

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (null!=token)
        {
            Claims claims = parseToken(token);
            // 解析对应的权限以及用户信息

            String userName=(String)claims.get("userName");
            SysUser sysUser=sysUserMapper.selectUserByName(userName);
            LoginUser loginUser=new LoginUser();
            loginUser.setSysUser(sysUser);
            return loginUser;
        }
        return null;
    }
    public String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
        return token;
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser)
    {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", loginUser.getSysUser().getLoginName());
        return createToken(claims);
    }




    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims)
    {
        long nowMillis = System.currentTimeMillis();
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(nowMillis+expire*1000))
                .signWith(SignatureAlgorithm.HS512, key).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token)
    {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }


}

