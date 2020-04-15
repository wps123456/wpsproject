package com.wps.studyplatform.stream;

public class UserEntity {
    private int id;
    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public UserEntity(int id, int number) {
        this.id = id;
        this.number = number;
    }
}
