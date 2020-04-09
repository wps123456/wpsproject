package com.wps.studyplatform.designpattern.factorypattren.abstractfactory;



public class Factory implements CarFactory{

    @Override
    public BCCar createBCCar() {
        return new BCCar();
    }

    @Override
    public BMWCar createBMWCar() {
        return new BMWCar();
    }
}
