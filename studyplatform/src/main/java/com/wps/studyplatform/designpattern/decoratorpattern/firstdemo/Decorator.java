package com.wps.studyplatform.designpattern.decoratorpattern.firstdemo;

public class Decorator implements Sweet {
    private Sweet sweet;
    public Decorator(Sweet sweet){
        this.sweet=sweet;
    }
    @Override
    public String getDescription(String name) {
        System.out.println("这是装饰类"+name);
        return "这是装饰类"+name;
    }
}
