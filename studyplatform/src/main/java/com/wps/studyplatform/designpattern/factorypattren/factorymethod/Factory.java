package com.wps.studyplatform.designpattern.factorypattren.factorymethod;


import com.wps.studyplatform.designpattern.factorypattren.normfactory.Car;

/**
 * 工厂接口，用于生产汽车
 */
public interface Factory {
    Car getCar(String name);
}
