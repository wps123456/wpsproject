package com.wps.studyplatform.designpattern.proxy.staticproxy;

/**
 * 房主（把房子交给中介的人）：被代理对象
 * 中介（租给你房子的人）：代理对象
 * 优点：
 *     1、可以隐藏目标的的具体实现
 *     2、可以在不修改目标类代码的情况下，对其增加新的功能。
 * 分类：
 *     静态代理、动态代理
 *     动态代理又分为：JDK动态代理和CGLIB动态代理
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        //多态
       RentalHouse rentalHouse=new Host();
       StaticProxy staticProxy=new StaticProxy(rentalHouse);
       staticProxy.rent();


    }
}
