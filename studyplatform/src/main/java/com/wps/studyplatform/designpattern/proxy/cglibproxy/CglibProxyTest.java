package com.wps.studyplatform.designpattern.proxy.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;

public class CglibProxyTest {
    public static void main(String[] args) throws NoSuchMethodException {
        //cglib的核心
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Host.class);
        enhancer.setCallback(new CglibProxyFirst());


        Host host = (Host) enhancer.create();
        host.rent("100");

        System.out.println("-------第二种-------");
        Host host1=new Host();
        Host host2=new CglibProxySecond().creatHost(host1);
        host2.rent("122222222");

    }
}
