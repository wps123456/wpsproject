package com.wps.studyhttprequest.http.entity;

/**
 * @Title PlatformEntity
 * @Description
 * @auther wps
 * @Date 2020/12/2821:59
 */
public class PlatformEntity {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlatformEntity(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
