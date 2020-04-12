package com.wps.studyplatform.designpattern.decoratorpattern.seconddemo;

public class DecoratorDemoTest {
    public static void main(String[] args) {
        //制作原味煎饼
        ShreddedCake shreddedCake=new ShreddedCake();
        System.out.println(shreddedCake.description()+". 价格    "+shreddedCake.money());
        //制作鸡蛋煎饼
        Cake cake=new ShreddedCake();
        EggShreddedCake eggShreddedCake=new EggShreddedCake(cake);
        System.out.println(eggShreddedCake.description()+"   价格   "+eggShreddedCake.money());
        //制作牛肉灌饼
        BeffShreddedCake beffShreddedCake=new BeffShreddedCake(cake);
        System.out.println(beffShreddedCake.description()+"   价格   "+beffShreddedCake.money());
        //制作牛肉鸡蛋灌饼
        BeffShreddedCake beffEggCake=new BeffShreddedCake(eggShreddedCake);
        System.out.println(beffEggCake.description()+"   价格   "+beffEggCake.money());
        //制作苹果煎饼
        AppShreddedCake appShreddedCake=new AppShreddedCake(cake);
        System.out.println(appShreddedCake.description()+"  价格  "+cake.money());
        //制作苹果牛肉鸡蛋煎饼
        AppShreddedCake appBefEggCake =new AppShreddedCake(beffEggCake);
        System.out.println(appBefEggCake.description()+"  价格  "+appBefEggCake.money());



    }
}
