package com.wps.studyplatform.job.constants;

/**
 * @Title ScheduleStatus
 * @Description
 * @auther wps
 * @Date 2020/7/719:43
 */
public enum ScheduleStatus {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 暂停
     */
    PAUSE(1);
    private int value;

    ScheduleStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
