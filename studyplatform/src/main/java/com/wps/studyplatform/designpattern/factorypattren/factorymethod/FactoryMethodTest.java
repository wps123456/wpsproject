package com.wps.studyplatform.designpattern.factorypattren.factorymethod;

import com.wps.studyplatform.designpattern.factorypattren.normfactory.Car;

/**
 * 工厂方法：
 *          提供一个用于创建对象的接口(工厂接口)，让其实现类(工厂实现类)决定实例化哪一个类(产品类)，并且由该实现类创建对应类的实例。
 *          创建对象实例化，不再有同一个类产生，则是由不同的类（工厂）产生
 * 根据上述代码可以看出，不同品牌的汽车是由不同的工厂生产的，貌似又是很完美的。
 * 优点：
 *     增加新的模块时，不需要更改之前的代码，增加新的业务即可
 * 缺陷：
 *     但大家看一下测试类，当一个人想要去买一辆宝马汽车的时候， 那么他就要去找宝马工厂给他生产一辆，过几天又想要买一辆奔驰汽车的时候，
 *     又得跑到奔驰工厂请人生产，这无疑就增加了用户的操作复杂性。
 * 所以有没有一种方便用户操作的方法呢？这个时候抽象工厂模式就出现了。
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        BMWFactory bmwFactory=new BMWFactory();
        Car car=bmwFactory.getCar("BMW");
        System.out.println(car.getName());

        Factory factory=new BCFactory();
        Car car1= factory.getCar("BC");
        System.out.println(car1.getName());

    }
}
