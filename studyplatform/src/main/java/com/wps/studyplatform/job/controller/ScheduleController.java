package com.wps.studyplatform.job.controller;

import com.wps.studyplatform.job.entity.ScheduleJobEntity;
import com.wps.studyplatform.job.service.ScheduleJobService;
import com.wps.studyplatform.utils.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 定时任务
 */
@RequestMapping("/sys/scheduler")
public class ScheduleController {
    /**
     * 传参
     * beanName:"platWarnMessageTask"
     * createTime:"2019-06-27 09:49:11"
     * cronExpression:"0/30 * * * * ?"
     * jobId:222111
     * jobName:"告警信息定时任务"
     * methodName:"sendMail"
     * params:null
     * remark:"beizhu"
     * status:"1"
     * type:null
     */
    private static final Logger log= LoggerFactory.getLogger(ScheduleController.class);
    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 查看定时任务信息
     */
    @GetMapping("/info/{jobId}")
    public ApiResult info(@PathVariable("jobId") Long jobId){
        ScheduleJobEntity scheduleJobEntity=scheduleJobService.getById(jobId);
        return ApiResult.success(scheduleJobEntity);
    }
    /**
     * 保存定时任务
     */
    @PostMapping("/save")
    public ApiResult save(@RequestBody ScheduleJobEntity scheduleJobEntity);
    Valida
}
