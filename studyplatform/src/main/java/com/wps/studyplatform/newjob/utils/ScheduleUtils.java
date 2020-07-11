package com.wps.studyplatform.newjob.utils;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ScheduleUtils {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    /**
     * 开始定时任务
     */
    public String startJob(String cron,String jobName,String jobGroup,Class<? extends Job> jobClass) throws SchedulerException {
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        JobKey jobKey=new JobKey(jobName,jobGroup);
        if (!scheduler.checkExists(jobKey)){
            schedulerJob(cron,scheduler,jobName,jobGroup,jobClass);
            resumeJob(jobName,jobGroup);
            return "定时任务已创建，并执行";
        }else {
            resumeJob(jobName,jobGroup);
            return "定时任务已存在，恢复定时任务";
        }
    }
    /**
     * 移除定时任务
     */
    public void deleteJob (String jobName,String jobGroup) throws SchedulerException {
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        JobKey jobKey=new JobKey(jobName,jobGroup);
        scheduler.deleteJob(jobKey);
    }
    /**
     * 暂停定时任务
     */
    public void pauseJob(String jobName,String jobGroup) throws SchedulerException {
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        JobKey jobKey=new JobKey(jobName,jobGroup);
        scheduler.pauseJob(jobKey);
    }
    /**
     * 暂停所有的定时任务
     */
    public void pauseAllJob() throws SchedulerException {
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        scheduler.pauseAll();
    }
    /**
     * 恢复定时任务
     */
    public void resumeJob(String jobName,String jobGroup) throws SchedulerException {
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        JobKey jobKey=new JobKey(jobName,jobGroup);
        scheduler.resumeJob(jobKey);
    }
    /**
     * 恢复全部定时任务
     */
    public void resumeAllJob() throws SchedulerException {
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        scheduler.resumeAll();
    }
    /**
     * 清空所有当前scheduler对象下的定时任务【目前只有一个scheduler对象】
     */
    public void clearAll() throws SchedulerException {
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        scheduler.clear();
    }
    /**
     * 动态创建Job
     * 此处的任何配置乐意放到properties或者数据库中
     * Trigger:name和Group目前和job的name、group一致，之后可以扩展归类
     */
    public void schedulerJob(String cron,Scheduler scheduler,String jobName,String jobGroup,
                             Class<? extends Job> jobClass) throws SchedulerException {
        /**
         * 此处可以先通过任务名查询数据库，如果数据库中存在该任务，更新任务的配置以及触发器
         * 如果此时数据库中没有查到任务，则按照下面的步骤新建一个任务，并配置初始化的参数，并配置存到数据库中
         */
        JobDetail jobDetail=JobBuilder.newJob(jobClass).withIdentity(jobName,jobGroup).build();
        CronScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule(cron);
        CronTrigger cronTrigger=TriggerBuilder.newTrigger().withIdentity(jobName,jobGroup)
                                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

}
