package com.wps.studyplatform.sqlcollect.entity;

/**
 * @Title RespSmartAppApply
 * @Description
 * @auther wps
 * @Date 2020/7/320:16
 */
public class RespSmartAppApply extends SmartAppApply {
    private String applyLoginName;
    private String applyUserName;

    public String getApplyLoginName() {
        return applyLoginName;
    }

    public void setApplyLoginName(String applyLoginName) {
        this.applyLoginName = applyLoginName;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }
}
