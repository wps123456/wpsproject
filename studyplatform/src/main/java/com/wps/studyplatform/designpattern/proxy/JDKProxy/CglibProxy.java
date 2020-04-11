package com.wps.studyplatform.designpattern.proxy.JDKProxy;

import com.wps.studyplatform.designpattern.proxy.cglibproxy.CglibProxyFirst;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy {
    public RentalHouse creatRentalHouse(RentalHouse rentalHouse){


        //cglib的核心
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(rentalHouse.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("我是中介，收取500元中介费");
                Object object=methodProxy.invokeSuper(o,objects);
                System.out.println("我是中介，我又想收你1000块钱！");
                return object;
            }
        });

        RentalHouse rentalHouse1=(RentalHouse) enhancer.create();
        return rentalHouse1;


    }
}
