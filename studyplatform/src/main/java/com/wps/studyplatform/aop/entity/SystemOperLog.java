package com.wps.studyplatform.aop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class SystemOperLog implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 日志逐渐
     */
    private Long operId;
    /**
     * 操作模块
     */
    private String title;
    /**
     * 业务类型（0其他，1新增，2修改，3删除，4查询）
     */
    private Integer businessType;
    /**
     * 请求method
     */
    private String method;
    /**
     * 操作类型(0其他 1后台用户，2手机用户)
     */
    private Integer operatorType;
    /**
     * 操作人
     */
    private String operName;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 请求url
     */
    private String operurl;
    /**
     * 操作地址
     */
    private String operIp;
    /**
     * 操作地点
     */
    private String operLocation;
    /**
     * 请求参数
     */
    private String operParam;
    /**
     * 操作状态（0正常，1异常）
     */
    private Integer status;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer opertorType) {
        this.operatorType = opertorType;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOperurl() {
        return operurl;
    }

    public void setOperurl(String operurl) {
        this.operurl = operurl;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperLocation() {
        return operLocation;
    }

    public void setOperLocation(String operLocation) {
        this.operLocation = operLocation;
    }

    public String getOperParam() {
        return operParam;
    }

    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}
