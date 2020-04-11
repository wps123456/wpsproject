package com.wps.studyplatform.designpattern.proxy.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxySecond {
    /**
     * 代理类，RentalHouse为代理接口，要对里面的方法进行增强
     */

    public RentalHouse creatRentalHouse(RentalHouse rentalHouse){
        //代理类对象
        JDKProxySecond jdkProxySecond=new JDKProxySecond();

        RentalHouse rentalHouse1=(RentalHouse) Proxy.newProxyInstance(jdkProxySecond.getClass().getClassLoader(),
                rentalHouse.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("我是中介，收取500元中介费");
                        Object object=method.invoke(rentalHouse,args);
                        System.out.println("我是中介，我又想收你1000块钱！");

                        return object;
                    }
                });



        return rentalHouse1;
    }

}
