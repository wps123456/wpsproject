package com.wps.studyplatform.sqlcollect.entity;

/**
 * 接口与熔断模板的映射
 */

public class APIHysBean {
    private String id;
    private String apiId;
    private String hysId;
    private String groupKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getHysId() {
        return hysId;
    }

    public void setHysId(String hysId) {
        this.hysId = hysId;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }
}
