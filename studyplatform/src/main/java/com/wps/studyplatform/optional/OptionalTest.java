package com.wps.studyplatform.optional;

import com.wps.studyplatform.jwt.entity.SysUser;

import java.util.Optional;

/**
 * @Title OptionalTest
 * @Description java8中的新特性，简化对nullPointException的处理
 * @auther wps
 * @Date 2020/5/814:49
 */
public class OptionalTest {
    public static void main(String[] args) {
        //原始对空指针异常的处理模式
        SysUser  user=null;
        Long userId;
        if (null==user){
            userId=222L;
        }else {
            userId=user.getUserId();
        }
        System.out.println(userId);
        //引入Optional的操作
        Long id=Optional.ofNullable(user).map(sysUser -> sysUser.getUserId()).orElse(222L);
        System.out.println(id);


        //在Either类中使用Optional的泛型表达式，可以对每一个属性进行上述的简化操作。
        SysUser sysUser=new SysUser(111L,"wps");
        Either<String,SysUser> either=new Either<>(Optional.of(sysUser.getLoginName()),Optional.of(sysUser));



    }
}
