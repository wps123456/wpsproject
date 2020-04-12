package com.wps.studyplatform.designpattern.decoratorpattern.firstdemo;

public class Cake implements Sweet {
    @Override
    public String getDescription(String name) {
        System.out.println("这是一个蛋糕"+name);
        return "蛋糕名"+name;
    }
}
