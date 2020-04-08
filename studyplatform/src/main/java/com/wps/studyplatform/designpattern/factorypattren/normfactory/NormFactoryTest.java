package com.wps.studyplatform.designpattern.factorypattren.normfactory;
/**
 * 简单工厂模式的定义：提供一个创建对象实例的功能，而无须关心其具体实现。被创建实例的类型可以是接口、抽象类，也可以是具体的类
 * 缺陷：
 *     每一个汽车品牌都有自己的生产工厂，都有自己生产技术。映射到spring框架中，我们有很多很多种的bean需要生产，
 *     如果只依靠一个简单工厂来实现，那么我们得在工厂类中嵌套多少个if..else if啊？
 *     更改时开闭原则（对扩展开放、对修改封闭）
 * 解决：每个品牌应该有自己的生产类
 *       工厂方法模式出现
 */
public class NormFactoryTest {
    public static void main(String[] args) {
        Car car=NormFactory.getCar("BM");
        System.out.println(car.getName());
    }
}
