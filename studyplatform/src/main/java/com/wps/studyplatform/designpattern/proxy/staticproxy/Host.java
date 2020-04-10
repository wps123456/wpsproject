package com.wps.studyplatform.designpattern.proxy.staticproxy;

/**
 * 房东，出租（目标类、被代理类）
 */
public class Host implements RentalHouse {
    @Override
    public void rent() {
        System.out.println("我是房东，出租120的房子");

    }
}
