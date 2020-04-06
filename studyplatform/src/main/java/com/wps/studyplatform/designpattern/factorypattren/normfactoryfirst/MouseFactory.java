package com.wps.studyplatform.designpattern.factorypattren.normfactoryfirst;

import com.wps.studyplatform.designpattern.factorypattren.normfactoryfirst.enums.MouseEnums;

/**
 * 鼠标工厂
 * 生产鼠标的方法，所有的鼠标都通过该方法生成
 * 上述模式下，所有的鼠标都在同一个MouseFactory工厂下生产，有一个统一的create静态方法。
 * 在使用工厂时，不需要对工厂进行实例化，只需要调用该静态方法便可得到相应的产品。
 * 缺点：但如果用户需要添加新一类的产品，例如有一项华硕鼠标，工厂要生产该产品则需要改create函数，这有悖于设计原则的开闭原则。
 */
public class MouseFactory {
    public static Mouse createMouse (MouseEnums mouseEnums){
        if (mouseEnums.toString().equals("LX")){
            //多态，父类引用指向子类对象
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
