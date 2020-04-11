package com.wps.studyplatform.designpattern.proxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxySecond {
    public Host creatHost(Host host){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Host.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("我是中介，收取500元中介费");
                Object object=methodProxy.invokeSuper(o,objects);
                System.out.println("我是中介，我又想收你1000块钱！");
                return object;
            }
        });
        Host host1=(Host)enhancer.create();
        return host1;

    }
}
