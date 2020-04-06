package com.wps.studyplatform.designpattern.factorypattren.normfactorySecond;

import com.wps.studyplatform.designpattern.factorypattren.normfactorySecond.enums.MouseEnums;

/**
 * 鼠标工厂
 * 生产鼠标的方法，所有的鼠标都通过该方法生成
 */
public class MouseFactory {
    public static Mouse createMouse (MouseEnums mouseEnums){
        if (mouseEnums.toString().equals("LX")){
            Mouse mouse=new LXMouse();
            return mouse;
        }else if (mouseEnums.toString().equals("DE")){
            Mouse mouse=new DEMouse();
            return mouse;
        }else {
            return null;
        }


    }

}
