package com.wps.studyplatform.job.utils;

import com.wps.studyplatform.job.entity.ScheduleJobEntity;
import com.wps.studyplatform.job.service.ScheduleJobLogService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title ScheduleJob
 * @Description
 * @auther wps
 * @Date 2020/7/620:06
 */
@DisallowConcurrentExecution
@Component
public class ScheduleJob extends QuartzJobBean {
    private static final Logger logger=LoggerFactory.getLogger(ScheduleJob.class);
    private ExecutorService service= Executors.newSingleThreadExecutor();
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context.getMergedJobDataMap().get(ScheduleJobEntity.JOB_PARAMKEY);
        //获取spring bean
        ScheduleJobLogService scheduleJobLogService=SpriContextU

    }
}
