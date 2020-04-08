package com.wps.studyes.invoke;

public class Person {
    public String name;
    private String phone;

    public  String getId(String id){

        System.out.println(id);
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
