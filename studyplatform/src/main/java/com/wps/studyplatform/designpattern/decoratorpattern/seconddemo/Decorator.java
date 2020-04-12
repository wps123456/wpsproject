package com.wps.studyplatform.designpattern.decoratorpattern.seconddemo;
/**
 * 建立和饼的连接，得到原始饼的属性，添加属性的中介。
 * @author oooo
 *
 */
public class Decorator implements  Cake{
    private Cake cake;
    public Decorator(Cake cake){
        this.cake=cake;
    }
    @Override
    public String description() {

        return cake.description();
    }

    @Override
    public Double money() {
        return cake.money();
    }
}
