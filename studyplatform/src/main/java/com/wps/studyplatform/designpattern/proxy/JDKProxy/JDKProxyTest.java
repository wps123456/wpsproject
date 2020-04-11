package com.wps.studyplatform.designpattern.proxy.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxyTest {
    public static void main(String[] args) {
        RentalHouse rentalHouse=new Host();
        InvocationHandler invocationHandler =new JDKProxyFirst(rentalHouse);
        RentalHouse rentalHouse1=(RentalHouse) Proxy.newProxyInstance(
                                    invocationHandler.getClass().getClassLoader(),//代理类的类加载器
                                    rentalHouse.getClass().getInterfaces(),//被代理类的接口，如果有多个以代理类的形式传递
                                    invocationHandler); //代理类实例
        rentalHouse1.rent();

        System.out.println("------------------------");
        RentalHouse rentalHouse2=new JDKProxySecond().creatRentalHouse(rentalHouse);
        rentalHouse2.rent();
    }

}
