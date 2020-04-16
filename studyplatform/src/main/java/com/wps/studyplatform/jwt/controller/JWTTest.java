package com.wps.studyplatform.jwt.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTTest {
    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        //设置过期时间
        Long time=System.currentTimeMillis();
        Long expTime=time+1000*40;
        List<String> roles=new ArrayList<>();
        roles.add("admin");
        roles.add("system");
        JwtBuilder builder= Jwts.builder()
                .setId("888")      //第二部分（playload）
                .setSubject("小白")
                .setIssuedAt(new Date())
                //设置token的过期时间
                .setExpiration(new Date(expTime))
                //自定义claims,在实际应用中，一般设置角色
                .claim("roles",roles)
                .signWith(SignatureAlgorithm.HS256,"studyplatform");  //设置第一部分(header)、第三部分（signature）

        System.out.println( "token值："+builder.compact() );




        while (true){
            Claims claims=Jwts.parser().setSigningKey("studyplatform").parseClaimsJws(builder.compact()).getBody();
            System.out.println("id:"+ claims.getId());
            System.out.println("subject:"+ claims.getSubject());
            System.out.println("当前用户角色："+claims.get("roles"));
            System.out.println("签证时间："+sdf.format(claims.getIssuedAt()));
            System.out.println("过期时间："+sdf.format(claims.getExpiration()));
            System.out.println("当前时间："+sdf.format(new Date()));


            Thread.sleep(10000);
        }



    }
}
