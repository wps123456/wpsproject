package com.wps.studyplatform.job.scheduler;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;

/**
 * @Title DynamicJob
 * @Description
 * @auther wps
 * @Date 2020/7/820:22
 */
public class DynamicJob {
    //要执行类，实现job接口
    private Class<? extends Job> target;
    private String cronExperssion;
    private String jobGroup= Scheduler.DEFAULT_GROUP;
    private String jobName;

    private transient TriggerKey triggerKey;
    private transient JobDetail jobDetail;

    public DynamicJob(){

    }

    public DynamicJob(String jobName) {
        this.jobName = jobName;
    }
    public Class<? extends Job> target(){
        return target;
    }
    public DynamicJob target(Class<? extends Job> target){
        this.target=target;
        return this;
    }
    public DynamicJob cronExpression (String cronExperssion){
        this.cronExperssion=cronExperssion;
        return this;
    }
    public String jobGroup(){
        return jobGroup;
    }
    public DynamicJob jobGroup(String jobGroup){
        this.jobGroup=jobGroup;
        return this;
    }

}
