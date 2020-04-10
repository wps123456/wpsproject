package com.wps.studyplatform.designpattern.proxy.staticproxy;

/**
 * 静态代理（中介、代理类）
 * 需要实现同一个借口
 */
public class StaticProxy implements RentalHouse{
    private RentalHouse rentalHouse;
    public StaticProxy(RentalHouse rentalHouse){
        this.rentalHouse=rentalHouse;
    }
    @Override
    public void rent() {
        System.out.println("我是中介，收取500元中介费");
        //调用被代理类的租房方法
        rentalHouse.rent();
        System.out.println("我是中介，我又想收你1000块钱！");

    }
}
