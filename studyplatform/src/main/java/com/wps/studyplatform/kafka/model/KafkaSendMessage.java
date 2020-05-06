package com.wps.studyplatform.kafka.model;

import java.util.Date;

/**
 * @Title KafkaSendMessage
 * @Description
 * @auther wps
 * @Date 2020/5/616:27
 */
public class KafkaSendMessage {
    private Long id;
    private String key;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
