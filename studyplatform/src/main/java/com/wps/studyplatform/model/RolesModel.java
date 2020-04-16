package com.wps.studyplatform.model;

import java.util.List;

public class RolesModel {
    private String loginName;
    private List<Long> roles;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    public RolesModel(String loginName, List<Long> roles) {
        this.loginName = loginName;
        this.roles = roles;
    }
}
