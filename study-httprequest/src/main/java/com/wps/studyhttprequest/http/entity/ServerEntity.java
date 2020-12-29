package com.wps.studyhttprequest.http.entity;

import java.util.List;

/**
 * @Title ServerEntity
 * @Description
 * @auther wps
 * @Date 2020/12/2821:58
 */
public class ServerEntity {
    private String type;
    private List<TypeEntity> instance;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TypeEntity> getInstance() {
        return instance;
    }

    public void setInstance(List<TypeEntity> instance) {
        this.instance = instance;
    }
}
