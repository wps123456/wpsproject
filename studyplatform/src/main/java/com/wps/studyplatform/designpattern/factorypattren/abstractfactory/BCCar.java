package com.wps.studyplatform.designpattern.factorypattren.abstractfactory;

public class BCCar implements BC {
    @Override
    public String getBCCar() {
        System.out.println("生产奔驰车成功");
        return "生产BC车成功";
    }
}
