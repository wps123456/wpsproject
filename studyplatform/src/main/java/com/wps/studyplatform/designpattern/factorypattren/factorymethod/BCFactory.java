package com.wps.studyplatform.designpattern.factorypattren.factorymethod;

import com.wps.studyplatform.designpattern.factorypattren.normfactory.BCCar;
import com.wps.studyplatform.designpattern.factorypattren.normfactory.Car;

public class BCFactory implements Factory {
    @Override
    public Car getCar(String name) {
        return new BCCar();
    }
}
