package com.wps.studyplatform.designpattern.proxy.staticproxy;

/**
 *需要实现的共同接口
 *因为要保证代理类要不改变被代理类原来功能的基础上增加新的功能
 */
public interface RentalHouse {
    void rent();
}
