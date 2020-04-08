package com.wps.studyplatform.designpattern.factorypattren.normfactory;


public class NormFactory {
    public static Car getCar(String name){
        if("BC".equals(name)){
            return new BCCar();
        }else if ("BMW".equals(name)){
            return new BMWCar();
        }else {
            System.out.println("此工厂无法生产："+name);
            return null;
        }
    }

}
