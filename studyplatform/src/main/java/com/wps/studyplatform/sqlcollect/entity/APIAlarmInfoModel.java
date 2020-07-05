package com.wps.studyplatform.sqlcollect.entity;

import java.security.Timestamp;

/**
 * api告警管理model层
 */
public class APIAlarmInfoModel {
    private String id;
    private int apiRegisteredCount;
    private double registeredPercentage;
    private boolean examineStatus;
    private Timestamp alarmTime;
    private String alarmMsg="服务注册量超过阈值";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getApiRegisteredCount() {
        return apiRegisteredCount;
    }

    public void setApiRegisteredCount(int apiRegisteredCount) {
        this.apiRegisteredCount = apiRegisteredCount;
    }

    public double getRegisteredPercentage() {
        return registeredPercentage;
    }

    public void setRegisteredPercentage(double registeredPercentage) {
        this.registeredPercentage = registeredPercentage;
    }

    public boolean isExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(boolean examineStatus) {
        this.examineStatus = examineStatus;
    }

    public Timestamp getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Timestamp alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmMsg() {
        return alarmMsg;
    }

    public void setAlarmMsg(String alarmMsg) {
        this.alarmMsg = alarmMsg;
    }
}
