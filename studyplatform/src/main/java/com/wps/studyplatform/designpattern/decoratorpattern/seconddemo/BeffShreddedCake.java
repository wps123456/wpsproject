package com.wps.studyplatform.designpattern.decoratorpattern.seconddemo;

/**
 * 牛肉味手抓饼
 * @author oooo
 *添加牛肉属性
 */
public class BeffShreddedCake extends Decorator {
    private Cake cake;
    public BeffShreddedCake(Cake cake) {
        super(cake);
        this.cake=cake;

    }
    @Override
    public String description() {
        return "牛肉"+cake.description();
    }
    @Override
    public Double money() {
        return cake.money()+3.0;
    }
}
