package com.wps.studyplatform.designpattern.factorypattren.normfactorySecond;

import com.wps.studyplatform.designpattern.factorypattren.normfactorySecond.enums.MouseEnums;

public class NormFactoryTest {
    public static void main(String[] args) {
        Mouse mouse= MouseFactory.createMouse(MouseEnums.DE);
        mouse.sayHello();
    }
}
