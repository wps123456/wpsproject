package com.wps.studyplatform.sqlcollect.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Title SmartAppApply
 * @Description
 * @auther wps
 * @Date 2020/7/320:17
 */
@TableName("smart_app_apply")
public class SmartAppApply {
   @TableId("id")
    private long id;

   @TableField("app_name")
    private String appName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
