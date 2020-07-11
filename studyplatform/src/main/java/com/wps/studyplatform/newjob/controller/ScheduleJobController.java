package com.wps.studyplatform.newjob.controller;

import com.wps.studyplatform.job.entity.ScheduleJobEntity;
import com.wps.studyplatform.newjob.mode.SchedulerJobEntity;
import com.wps.studyplatform.newjob.task.PrintHello;
import com.wps.studyplatform.newjob.utils.ScheduleUtils;
import com.wps.studyplatform.utils.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caor/job")
public class ScheduleJobController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private static final Logger log= LoggerFactory.getLogger(ScheduleJobController.class);
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private ScheduleUtils scheduleUtils;

    @ApiOperation("创建定时任务并启动")
    @PostMapping("/start/reallyJob")
    public ApiResult startReallyJob(@RequestBody SchedulerJobEntity schedulerJobEntity) throws SchedulerException {

        //  */5 * * * * ?
        // PrintHello
        // PrintHelloGroup
        String cron=schedulerJobEntity.getCron();
        String jobName=schedulerJobEntity.getJobName();
        String jobGroup=schedulerJobEntity.getJobGroup();
        String info=scheduleUtils.startJob(cron,jobName,jobGroup, PrintHello.class);
        return ApiResult.success(info,null);

    }
    @ApiOperation("清除某个定时任务")
    @PostMapping("/clear/oneJob")
    public ApiResult clearOneJob(@RequestBody SchedulerJobEntity schedulerJobEntity) throws SchedulerException {
        String info=null;
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        String jobName=schedulerJobEntity.getJobName();
        String jobGroup=schedulerJobEntity.getJobGroup();
        JobKey jobKey=new JobKey(jobName,jobGroup);
        if (scheduler.checkExists(jobKey)){
            scheduleUtils.deleteJob(jobName,jobGroup);
            log.info("---------定时任务清除完毕----------");
            return ApiResult.success(info,null);
        }else {
            info="该定时任务不存在，没有必要清除";
            log.info("---------------"+info+"--------------");
            return ApiResult.fail(info,null);
        }
    }
    @ApiOperation("清除全部定时任务")
    @GetMapping("/clear/allJob")
    public ApiResult clearAllJob() throws SchedulerException {
        scheduleUtils.clearAll();
        log.info("--------------清除定时任务成功---------");
        return ApiResult.success("清除定时任务城");
    }
    @ApiOperation("恢复某个定时任务")
    @PostMapping("/resumeJob")
    public ApiResult resumeJob(@RequestBody SchedulerJobEntity schedulerJobEntity) throws SchedulerException {
        String info=null;
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        String jonName=schedulerJobEntity.getJobName();
        String jobGroup=schedulerJobEntity.getJobGroup();
        TriggerKey triggerKey=new TriggerKey(jonName,jobGroup);
        Trigger.TriggerState jobState=scheduler.getTriggerState(triggerKey);
        if (jobState.toString().equals("NORMAL")){
            info="该定时任务已生效，无须重复恢复";
        }
        if (jobState.toString().equals("PAUSED")){
            info="该定时任务已暂停，现已恢复";
        }
        if (jobState.toString().equals("NONE")){
            info="该定时任务不存在，无法恢复";
        }
        return ApiResult.success(info,null);

    }
    @ApiOperation("恢复全部定时任务")
    @GetMapping("/resume/allJob")
    public ApiResult resumeAllJob() throws SchedulerException {
        scheduleUtils.resumeAllJob();
        log.info("------------");
        return ApiResult.success("定时任务全部恢复",null);
    }
    @ApiOperation("暂停某个定时任务")
    @PostMapping("/pauseJob")
    public ApiResult pauseJob(@RequestBody SchedulerJobEntity schedulerJobEntity) throws SchedulerException {
        String info;
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        String jonName=schedulerJobEntity.getJobName();
        String jobGroup=schedulerJobEntity.getJobGroup();
        JobKey jobKey=new JobKey(jonName,jobGroup);
        if (scheduler.checkExists(jobKey)){
            scheduleUtils.pauseJob(jonName,jobGroup);
            info="该定时任务暂停成功";
            return ApiResult.success(info);
        }else {
            info="该定时任务不存在，不需要暂停该任务";
            return ApiResult.fail(info);
        }

    }
    @ApiOperation("暂停全部的定时任务")
    @GetMapping("/pause/allJob")
    public ApiResult pauseAllJob() throws SchedulerException {
        scheduleUtils.pauseAllJob();
        return ApiResult.success("暂停全部定时任务成功");
    }
    @ApiOperation("查看定时任务的状态")
    @PostMapping("/show/jobState")
    public ApiResult showJobState(@RequestBody SchedulerJobEntity schedulerJobEntity) throws SchedulerException {
        String info=null;
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey=new TriggerKey(schedulerJobEntity.getJobName(),schedulerJobEntity.getJobGroup());
        Trigger.TriggerState jobState=scheduler.getTriggerState(triggerKey);
        if (jobState.toString().equals("NORMAL")){
            info="该定时任务已生效";
        }else if (jobState.toString().equals("PAUSED")){
            info="该定时任务已暂停";
        }else if (jobState.toString().equals("NONE")){
            info="该定时任务不存在";
        }else {
            info=jobState.toString();
        }
        return ApiResult.success(info,null);

    }



}
