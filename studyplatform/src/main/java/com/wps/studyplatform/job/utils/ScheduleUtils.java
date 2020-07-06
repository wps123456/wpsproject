package com.wps.studyplatform.job.utils;

import com.wps.studyplatform.exception.base.BaseException;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


}
