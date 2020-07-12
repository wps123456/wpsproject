package com.wps.studyplatform.curdxml.entity;

public class StudentEntity {
    private String name;
    private int age;
    private boolean isBoy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isBoy() {
        return isBoy;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isBoy=" + isBoy +
                '}';
    }

    public void setBoy(boolean boy) {
        isBoy = boy;
    }

}
