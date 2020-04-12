package com.wps.studyplatform.designpattern.decoratorpattern.firstdemo;

public class DecoratorTest {
    public static void main(String[] args) {
        Sweet sweet=new Cake();
        FruitDecorator fruitDecorator=new FruitDecorator(sweet);
        fruitDecorator.getDescription("制作水果");
    }
}