package com.wps.studyplatform.designpattern.factorypattren.abstractfactory;

public class BMWCar implements BMW {
    @Override
    public String getBMWCar() {
        System.out.println("生产BMW成功");
        return "生产BMW成功";
    }
}
