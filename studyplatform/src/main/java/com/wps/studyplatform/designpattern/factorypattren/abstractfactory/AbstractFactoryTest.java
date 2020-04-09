package com.wps.studyplatform.designpattern.factorypattren.abstractfactory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        //生产BMW车
         Factory  factory =new Factory();
         factory.createBMWCar().getBMWCar();
         //生产BC
         factory.createBCCar().getBCCar();


    }
}
