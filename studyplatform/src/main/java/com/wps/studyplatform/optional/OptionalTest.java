package com.wps.studyplatform.optional;

import com.wps.studyplatform.jwt.entity.SysUser;

import java.util.Optional;

/**
 * @Title OptionalTest
 * @Description java8中的新特性，简化对nullPointException的处理,目的是为了杜绝空指针异常
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

        /**
         * Optional的构造方法
         * 1：Optional.of(T)，该方法的入参不能为null，否则胡有NPE
         * 2：Optional.ofNullable(T) 参数可以为null，当入参不确定是可以使用
         * 3：Optional.empty() 这种方式是返回一个空Optional，等效Optional.ofNullable(null)
         */

        SysUser sysUser1=new SysUser(1L,"wps");
        Optional<SysUser> userOpt=Optional.of(sysUser1);



    }
}
