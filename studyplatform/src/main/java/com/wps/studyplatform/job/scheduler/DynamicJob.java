package com.wps.studyplatform.job.scheduler;

import org.quartz.*;

import java.util.Map;

/**
 * @Title DynamicJob
 * @Description
 * @auther wps
 * @Date 2020/7/820:22
 */
public class DynamicJob {
    //要执行类，实现job接口
    private Class<? extends Job> target;
    private String cronExpression;
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
        this.cronExpression=cronExperssion;
        return this;
    }
    public String jobGroup(){
        return jobGroup;
    }
    public DynamicJob jobGroup(String jobGroup){
        this.jobGroup=jobGroup;
        return this;
    }
    public String jobName(){
        return jobName;
    }
    public DynamicJob jobName(String jobName){
        this.jobName=jobName;
        return this;
    }
    public TriggerKey triggerKey(){
        if (triggerKey==null){
            triggerKey=TriggerKey.triggerKey(this.jobName,this.jobGroup);
        }
        return triggerKey;
    }
    public JobDetail jobDetail(){
        if (jobDetail==null){
            jobDetail= JobBuilder.newJob(target)
                    .withIdentity(this.jobName,this.jobGroup)
                    .build();
        }
        return jobDetail;
    }

    /**
     * 传参给执行的job
     * 在job中通过context.getMergedJobDataMap().get(key)获取值
     * @param key
     * @param value
     * @return
     */
    public DynamicJob addJobData(String key,Object value){
        final JobDetail detail=jobDetail();
        final JobDataMap jobDataMap=detail.getJobDataMap();
        jobDataMap.put(key,value);
        return this;
    }

    /**
     * 传参给执行的job
     * 在job中通过context.getMerfedJobDataMap().get(key)获取值
     * @param map
     * @return
     */
    public DynamicJob addJobDataMap(Map<String,Object> map){
        final JobDetail detail=jobDetail();
        final JobDataMap jobDataMap=detail.getJobDataMap();
        jobDataMap.putAll(map);
        return this;

    }
    public String cronExpression(){
        return this.cronExpression;
    }
    public CronTrigger cronTrigger(){
        final CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder.cronSchedule(this.cronExpression);
        return TriggerBuilder.newTrigger().withIdentity(triggerKey())
                .withSchedule(cronScheduleBuilder)
                .build();
    }

    @Override
    public String toString() {

        final StringBuilder sb=new StringBuilder();
        sb.append("{target=").append(target);
        sb.append(", cronExperssion='").append(cronExpression).append('\'');
        sb.append(", jobGroup='").append(jobGroup).append('\'');
        sb.append(", jobName='").append(jobName).append('\'');
        sb.append("}");
        return sb.toString();
    }
}
