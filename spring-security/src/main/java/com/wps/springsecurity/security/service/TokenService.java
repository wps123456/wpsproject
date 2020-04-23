package com.wps.springsecurity.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wps.springsecurity.constant.SecurityConstant;
import com.wps.springsecurity.redis.RedisCache;
import com.wps.springsecurity.security.LoginUser;
import com.wps.springsecurity.testcontroller.entity.SysUser;
import com.wps.springsecurity.testcontroller.mapper.SysUserMapper;
import com.wps.springsecurity.utils.IdUtils;
import com.wps.springsecurity.utils.UUID;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
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
    private int expire ;//30分钟

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisCache redisCache;

    /**
     * 获取用户身份信息
     * 方案一：通过解析token中的userName，然后再查询mysql数据库获取用户信息
     *             String userName=(String)claims.get("userName");
     *             SysUser sysUser=sysUserMapper.selectUserByName(userName);
     *             LoginUser loginUser=new LoginUser();
     *             loginUser.setSysUser(sysUser);
     * 方案二：通过解析token中的uuid、userKey，然后从redis中获取用户的信息。
     *         这时候就遇到一个问题，每次访问后，token的过期时间应该刷新，
     *         方案一：后端直接刷新token值，返回给前端，此方案不可取，需要单独写接口时间。
     *         方案二：创建token时，不赋予过期时间，将过期时间放在redis。
     *                 在redis保存用户信息LoginUser时，同时保存了过期时间。
     *                 当redis获取到的用户信息为空时，则认定token过期。
     *                 不为空时，逻辑继续进行，此时刷新redis的过期时间。
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

            //方案二：
            String uuid=(String)claims.get("uuid");
            String userKey=SecurityConstant.LOGIN_USER_KEY+uuid;
            LoginUser loginUser=new LoginUser();
            loginUser=redisCache.getCacheObject(userKey);
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
        //将用户信息保存到redis中，claims中存放用户信息对应的key值。
        String uuid=IdUtils.simpleUUID();
        String userKey=SecurityConstant.LOGIN_USER_KEY+uuid;
        loginUser.setUUID(uuid);
        redisCache.setCacheObject(userKey,loginUser,expire,TimeUnit.MINUTES);

        Map<String, Object> claims = new HashMap<>();
        claims.put("uuid", uuid);
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
//方案二：token中不设置过期时间，在redis中设置过期时间。
//                .setExpiration(new Date(nowMillis+expire*1000))       /
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


    /**
     * 刷新redis中的缓存时间。
     * 情景：当用户带着token值正常访问时，访问后要刷新token的过期时间，这里只需要刷新redis中的过期时间就行
     * @param loginUser 登录者信息
     */
    public void verifyToken(LoginUser loginUser) {
        String uuid=loginUser.getUUID();
        String userKey=SecurityConstant.LOGIN_USER_KEY+uuid;
        redisCache.setCacheObject(userKey,loginUser,expire,TimeUnit.MINUTES);
    }
}

