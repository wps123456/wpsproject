package com.wps.studyplatform.sqlcollect.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title BaseEntity
 * @Description
 * @auther wps
 * @Date 2020/7/319:34
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID=1L;

    private String createBy;

    /**
     * 创建时间
     * 增加timezong属性 解决返回之间与查出时间不一致的问题
     */
    @TableField(update = "now()" ,fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String updateBy;

    /**
     * 更新时间
     * 增加timezong属性 解决返回之间与查出时间不一致的问题
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 备注
     * 增加注解 忽略null 值的判断 当remart为努null 也进行更新
     */
    @TableField(strategy = FieldStrategy.IGNORED)
    private String remark;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
