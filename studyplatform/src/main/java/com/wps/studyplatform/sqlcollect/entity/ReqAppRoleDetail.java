package com.wps.studyplatform.sqlcollect.entity;

import java.io.Serializable;

public class ReqAppRoleDetail implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 角色id
     */
    private Long appId;
    /**
     * app状态（0禁止访问，1允许访问）
     */
    private String appStatus;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }
}
