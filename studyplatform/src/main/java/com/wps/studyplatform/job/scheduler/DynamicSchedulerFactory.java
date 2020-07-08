package com.wps.studyplatform.job.scheduler;

import io.jsonwebtoken.lang.Assert;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;

/**
 * @Title DynamicSchedulerFactory
 * @Description
 * @auther wps
 * @Date 2020/7/820:19
 */
public class DynamicSchedulerFactory implements InitializingBean {

    private static final Logger LOG =LoggerFactory.getLogger(DynamicSchedulerFactory.class);
    private static Scheduler scheduler;
    public DynamicSchedulerFactory(){

    }
    public static boolean registerJob(DynamicJob job)throws SchedulerException{
        final TriggerKey triggerKey =job.triggerKey();
        if (scheduler.checkExists(triggerKey)){
            final Trigger trigger=scheduler.getTrigger(triggerKey);
            throw new SchedulerException(trigger+"已经存在");
        }
        final CronTrigger cronTrigger=job.cronTrigger();
        final JobDetail jobDetail=job.jobDetail();
        final Date date=scheduler.scheduleJob(jobDetail,cronTrigger);
        LOG.debug("Register DynamicJoB {} on [{}]",job,date);
        return true;
    }

    /**
     * check the job is exist or not
     * @throws Exception
     */
    public static boolean existJob(DynamicJob job) throws SchedulerException {
        final TriggerKey triggerKey=job.triggerKey();
        return scheduler.checkExists(triggerKey);
    }
    public static boolean pauseJob(DynamicJob existJob) throws SchedulerException {
        final TriggerKey triggerKey=existJob.triggerKey();
        boolean result=false;
        if (scheduler.checkExists(triggerKey)){
            scheduler.pauseTrigger(triggerKey);
            result=true;
            LOG.debug("Pause exist DynamicJob {},triggerKey [{}] successful",existJob,triggerKey);
        }else {
            LOG.debug("Failed pause exist DynamicJob {},because not fount triggerKey [{}]",existJob,triggerKey);
        }
        return result;
    }

    public static boolean resumeJob(DynamicJob existJob) throws SchedulerException {
        final TriggerKey triggerKey=existJob.triggerKey();
        boolean result=false;
        if (scheduler.checkExists(triggerKey)){
            final CronTrigger newTrigger=existJob.cronTrigger();
            final Date date=scheduler.rescheduleJob(triggerKey,newTrigger);
            result=true;
            LOG.debug("Resume exist DynamicJob {},triggerKey [{}] on [{}] successful",existJob,triggerKey,date);
        }else {
            LOG.debug("Failed resume exist DynamicJob {},because not fount triggerKey [{}]",existJob,triggerKey);
        }
        return result;
    }

    /**
     * 删除吊已经存在的job
     * @param existJob
     * @return
     * @throws SchedulerException
     */
    public static boolean removeJob(DynamicJob existJob) throws SchedulerException {
        final TriggerKey triggerKey=existJob.triggerKey();
        boolean result=false;
        if (scheduler.checkExists(triggerKey)){
            result=scheduler.unscheduleJob(triggerKey);
        }
        LOG.debug("Remove DynamicJob {} result [{}]",existJob,result);
        return result;
    }
    public void setScheduler(Scheduler scheduler){
        DynamicSchedulerFactory.scheduler=scheduler;
    }





    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(scheduler,"scheduler is null");
        LOG.info("initial dynamicSchedulerFactory successful,scheduler instance:{}",scheduler);

    }
}
