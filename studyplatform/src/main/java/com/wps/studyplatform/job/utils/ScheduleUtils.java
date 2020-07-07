package com.wps.studyplatform.job.utils;

import com.wps.studyplatform.exception.base.BaseException;
import com.wps.studyplatform.job.constants.ScheduleStatus;
import com.wps.studyplatform.job.entity.ScheduleJobEntity;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 定时任务工具类
 */
public class ScheduleUtils {

    private final static String JOB_NAME="TASK_";
    private static final Logger log= LoggerFactory.getLogger(ScheduleUtils.class);
    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long jobId){
        return TriggerKey.triggerKey(JOB_NAME+jobId);
    }
    public static TriggerKey getTriggerKey(Long jobId,String beanName){
        return TriggerKey.triggerKey(JOB_NAME+jobId,beanName);
    }
    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId){
        return JobKey.jobKey(JOB_NAME+jobId);
    }
    public static JobKey getJobKey(Long jobId,String beanName){
        return JobKey.jobKey(JOB_NAME+jobId,beanName);
    }
    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler,Long jobId){
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            log.info(e.getMessage(),e);
            throw new BaseException("获取定时任务CronTrigger出现异常",e);
        }
    }
    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJobEntity scheduleJob){
        try {
            //构建job信息
            JobDetail jobDetail=JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(scheduleJob.getJobId())).build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger=TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getJobId()))
                               .withSchedule(scheduleBuilder).build();
            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleJobEntity.JOB_PARAMKEY,scheduleJob);

            Date date =scheduler.scheduleJob(jobDetail,trigger);
            //暂停任务
            if (scheduleJob.getStatus()==ScheduleStatus.PAUSE.getValue()){
                pauseJob(scheduler,scheduleJob.getJobId());
            }
        }catch (SchedulerException e){
            throw new BaseException("创建定时任务失败",e);
        }

    }

    /**
     * 更新定时任务
     * @param scheduler
     * @param scheduleJob
     */

    public static void updateScheduleJob(Scheduler scheduler,ScheduleJobEntity scheduleJob){
        try {
            TriggerKey triggerKey=getTriggerKey(scheduleJob.getJobId());
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder=CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger=getCronTrigger(scheduler,scheduleJob.getJobId());
            //安心的cronExpression表达式重新构建trigger
            trigger=trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //参数
            trigger.getJobDataMap().put(ScheduleJobEntity.JOB_PARAMKEY,scheduleJob);

            scheduler.rescheduleJob(triggerKey,trigger);
            //暂停任务
            if (scheduleJob.getStatus()==ScheduleStatus.PAUSE.getValue()){
                pauseJob(scheduler,scheduleJob.getJobId());
            }
        }catch (SchedulerException e){
            throw new BaseException("更新定时任务失败",e);
        }
    }
    /**
     * 立即执行任务
     * @param scheduler
     * @param scheduleJob
     */
    public static void run(Scheduler scheduler,ScheduleJobEntity scheduleJob){
        try {
            //参数
            JobDataMap dataMap=new JobDataMap();
            dataMap.put(ScheduleJobEntity.JOB_PARAMKEY,scheduleJob);
            scheduler.triggerJob(getJobKey(scheduleJob.getJobId()),dataMap);
        }catch (SchedulerException e){
            throw new BaseException("立即执行定时任务失败",e);
        }
    }


    /**
     * 暂停任务
     * @param scheduler
     * @param jobId
     */
    private static void pauseJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        }catch (SchedulerException e){
            throw new BaseException("暂停定时任务失败",e);
        }
    }

    /**
     * 恢复任务
     * @param scheduler
     * @param jobId
     */
    public static void resumeJob(Scheduler scheduler,Long jobId){
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new BaseException("恢复定时任务失败",e);
        }
    }

    /**
     * 删除定时任务
     * @param scheduler
     * @param jobId
     */
    public static void deleteScheduleJob(Scheduler scheduler,Long jobId){
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new BaseException("删除定时任务失败",e);
        }
    }

    /**
     * 创建定时任务
     * @param scheduler
     * @param job
     * @throws SchedulerException
     */
    public static void createScheduleJob2(Scheduler scheduler,ScheduleJobEntity job) throws SchedulerException {
        //从数据库中注册的所有JOB
        JobKey jobKey=getJobKey(job.getJobId(),job.getBeanName());
        JobDetail jobDetail=getJobDeatil(jobKey,job);

        Trigger trigger=getTrigger(job);
        if(job.getStatus()==ScheduleStatus.NORMAL.getValue()){
            scheduler.scheduleJob(jobDetail,trigger);
        }else {
            throw new BaseException("创建定时任务失败");
        }

    }



    private static JobDetail getJobDeatil(JobKey jobKey, ScheduleJobEntity job) {
        JobDataMap map=new JobDataMap();
        //放入参数，运行时的方法可以获取
        map.put(ScheduleJobEntity.JOB_PARAMKEY,job);
        return JobBuilder.newJob(ScheduleJob.class)
                .withIdentity(jobKey)
                .withDescription(job.getJobName())
                .setJobData(map)
                .storeDurably()
                .build();
    }


    //获取Trigger（JOB的触发器，执行规则）
    private static Trigger getTrigger(ScheduleJobEntity job) {
        //按新的cronExpression表达式构建一个新的trigger
        return TriggerBuilder.newTrigger()
                .withIdentity(getTriggerKey(job.getJobId()))
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                .build();
    }


}
