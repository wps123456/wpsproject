package com.wps.studyplatform.job.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title ScheduleJobEntity
 * @Description
 * @auther wps
 * @Date 2020/7/619:21
 */
@TableName("schedule_job")
public class ScheduleJobEntity implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAMKEY="JOB_PARAM+KEY";
    /**
     * 任务id
     */
    @TableId
    private Long jobId;

    @NotBlank(message = "任务名称不能为空")
    @Length(min = 1,max = 64,message = "任务名称应为1-64个字符")
    @Pattern(regexp = "^[\\w\\u4e00-\\u9fa5\\-_][\\s\\w\\u4e00-\\u9fa5\\-_]*(?!\\s)$",
             message = "请属于中文、英文、数字、空格、下划线和连接线，其中首部不能为空格")
    private String jobName;

    /**
     * spring bean名称
     */
    @NotBlank(message = "bean名称不能为空")
    @Length(min = 1,max = 64,message = "bean名称应为1-64个字符")
    @Pattern(regexp = "^[0-9a-zA-Z_\\-]{1,}$",message = "请输入英文字母、数字、连接线或者下划线")
    private String beanName;

    /**
     * 方法名
     */
    @NotBlank(message = "方法名不能为空")
    @Length(min = 1,max = 64,message = "方法名称应为1-64个字符")
    @Pattern(regexp = "^[0-9a-zA-Z_\\-]{1,}$",message = "请输入英文字母、数字、连接线或者下划线")
    private String methodName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
    @NotBlank(message = "cron不能为空")
    private String cronExpression;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String tpye;

    public static String getJobParamkey() {
        return JOB_PARAMKEY;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTpye() {
        return tpye;
    }

    public void setTpye(String tpye) {
        this.tpye = tpye;
    }
}
