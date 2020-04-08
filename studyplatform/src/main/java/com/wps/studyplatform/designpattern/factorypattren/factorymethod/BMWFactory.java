package com.wps.studyplatform.designpattern.factorypattren.factorymethod;

import com.wps.studyplatform.designpattern.factorypattren.normfactory.BMWCar;
import com.wps.studyplatform.designpattern.factorypattren.normfactory.Car;

public class BMWFactory implements Factory{
    @Override
    public Car getCar(String name) {
        return new BMWCar();
    }
}
