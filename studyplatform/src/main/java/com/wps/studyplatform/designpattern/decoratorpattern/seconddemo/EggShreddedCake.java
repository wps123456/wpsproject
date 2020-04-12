package com.wps.studyplatform.designpattern.decoratorpattern.seconddemo;
/**
 * 鸡蛋手抓饼类
 * @author oooo
 *添加鸡蛋属性
 */

public class EggShreddedCake extends Decorator{
    private Cake cake;
    public EggShreddedCake(Cake cake) {
        super(cake);
        this.cake=cake;
    }
    @Override
    public String description() {
        return "鸡蛋"+cake.description();
    }
    @Override
    public Double money() {
        return cake.money()+1.5;
    }
}


