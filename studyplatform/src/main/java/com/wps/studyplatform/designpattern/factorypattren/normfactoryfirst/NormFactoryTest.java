package com.wps.studyplatform.designpattern.factorypattren.normfactoryfirst;

import com.wps.studyplatform.designpattern.factorypattren.normfactoryfirst.enums.MouseEnums;

public class NormFactoryTest {
    public static void main(String[] args) {
        Mouse mouse=MouseFactory.createMouse(MouseEnums.LX);
        mouse.sayHello();
    }
}
