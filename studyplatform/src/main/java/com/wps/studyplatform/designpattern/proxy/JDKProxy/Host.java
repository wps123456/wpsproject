package com.wps.studyplatform.designpattern.proxy.JDKProxy;

public class Host implements RentalHouse {
    @Override
    public void rent() {
        System.out.println("我是房东，出租120的房子");
    }
}
