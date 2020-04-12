package com.wps.studyplatform.designpattern.decoratorpattern.seconddemo;

public class AppShreddedCake extends Decorator {
    private Cake cake;
    public AppShreddedCake(Cake cake) {
        super(cake);
        this.cake=cake;
    }

    @Override
    public String description() {

        return "苹果"+cake.description();
    }

    @Override
    public Double money() {
        return cake.money()+2.5;
    }
}
