package com.wps.studyplatform.designpattern.decoratorpattern.seconddemo;

/**
 * 原味手抓饼类
 * @author oooo
 *负责接收附加属性的类
 */
public class ShreddedCake implements Cake{
    //实现Cake接口
    @Override
    public String description() {
        //描述属性
        return "原味手抓饼";
    }
    @Override
    public Double money() {
        //描述价格
        return 3.5;
    }
}

