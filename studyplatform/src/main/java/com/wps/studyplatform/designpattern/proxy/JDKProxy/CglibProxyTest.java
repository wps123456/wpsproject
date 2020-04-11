package com.wps.studyplatform.designpattern.proxy.JDKProxy;

public class CglibProxyTest {
    public static void main(String[] args) {
        RentalHouse rentalHouse=new Host();
        RentalHouse rentalHouse1=new CglibProxy().creatRentalHouse(rentalHouse);
        rentalHouse1.rent();
    }

}
