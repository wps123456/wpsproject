package com.wps.studyplatform.designpattern.factorypattren.abstractfactory;

/**
 * 抽象工厂：为创建一组相关或相互依赖的对象提供一个接口，而且无需指定他们的具体类。
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        //生产BMW车
         Factory  factory =new Factory();
         System.out.println(factory.createBMWCar().getBMWCar());
         System.out.println(factory.createBCCar().getBCCar());


    }
}
