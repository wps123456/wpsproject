package com.wps.studyplatform.designpattern.decoratorpattern.firstdemo;

public class FruitDecorator extends Decorator {
    Sweet sweet;
    public FruitDecorator(Sweet sweet) {

        super(sweet);
        this.sweet=sweet;
    }

    @Override
    public String getDescription(String name) {
        System.out.println("这是具体装饰类:"+name);
        super.getDescription(name);
        return sweet.getDescription(name);
    }
}
