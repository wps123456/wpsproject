package com.wps.studyplatform.designpattern.proxy.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxyFirst implements InvocationHandler {
    private RentalHouse rentalHouse;
    public JDKProxyFirst(RentalHouse rentalHouse){
        this.rentalHouse=rentalHouse;
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("我是中介，收取500元中介费");
        Object object=method.invoke(rentalHouse,args);
        System.out.println("我是中介，我又想收你1000块钱！");
        return object;
    }
}
