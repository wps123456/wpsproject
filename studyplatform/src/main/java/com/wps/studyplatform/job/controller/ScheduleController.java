package com.wps.studyplatform.job.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wps.studyplatform.exception.base.BaseException;
import com.wps.studyplatform.job.constants.ScheduleConstants;
import com.wps.studyplatform.job.entity.ScheduleJobEntity;
import com.wps.studyplatform.job.service.ScheduleJobService;
import com.wps.studyplatform.job.utils.SQLUtils;
import com.wps.studyplatform.job.utils.ValidatorUtils;
import com.wps.studyplatform.sqlcollect.config.PageController;
import com.wps.studyplatform.utils.*;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 */
@RequestMapping("/sys/scheduler")
public class ScheduleController extends PageController {
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
    @Autowired
    private IdWorker idWorker;

    /**
     * 定时任务列表
     */
    @PostMapping("/list")
    public PageUtils list(@RequestBody Map<String,Object> params){

        String jobName= (String) params.get("jobName");
        if (StringUtil.isNotEmpty(jobName)){
            jobName= SQLUtils.transUnderline(jobName);
        }
        IPage pageResult=scheduleJobService.
                page(Query(params),new QueryWrapper<ScheduleJobEntity>()
                        .like(StringUtil.isNotEmpty(jobName),"job_name",jobName).isNotNull("type"));
        return new PageUtils(pageResult);
    }

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
    public ApiResult save(@RequestBody ScheduleJobEntity scheduleJobEntity){
        ValidatorUtils.validateEntity(scheduleJobEntity);
        //校验定时任务
        if (!CronExpression.isValidExpression(scheduleJobEntity.getCronExpression())){
            throw new BaseException("cron表达式异常");
        }
        scheduleJobEntity.setJobId(idWorker.nextId());
        scheduleJobService.saveJob(scheduleJobEntity);

        log.info("保存定时任务成功"+scheduleJobEntity.toString());
        return ApiResult.success("成功");
    }
    /**
     * 修改定时任务
     */
    @PostMapping("/update")
    public ApiResult update(@RequestBody ScheduleJobEntity scheduleJobEntity){
        ValidatorUtils.validateEntity(scheduleJobEntity);
        //校验定时任务
        if (!CronExpression.isValidExpression(scheduleJobEntity.getCronExpression())){
            throw new BaseException(ScheduleConstants.CRON_ERROR);
        }
        log.info("修改定时任务"+scheduleJobEntity.toString());
        scheduleJobService.update(scheduleJobEntity);
        return ApiResult.success("true");
    }
    /**
     * 删除定时任务
     */
    @PostMapping("/delete")
    public ApiResult delete(@RequestBody Long[] jobIds){
        scheduleJobService.deleteBatch(jobIds);
        log.info("删除定时任务成功"+jobIds.toString());
        return ApiResult.success();
    }
    /**
     * 立即执行任务
     */
    @PostMapping("/run")
    public ApiResult run(@RequestBody Long[] jobIds){
        scheduleJobService.run(jobIds);
        log.info("执行定时任务成功"+jobIds.toString());
        return ApiResult.success();
    }
    /**暂时定时任务
     *
     */
    @PostMapping("/pause")
    public ApiResult pause(@RequestBody Long[] jobIds){
        scheduleJobService.pause(jobIds);
        log.info("暂停定时任务成功"+jobIds.toString());
        return ApiResult.success();
    }
    /**
     * 恢复定时任务
     */
    @PostMapping("/resume")
    public ApiResult resume(@RequestBody Long[] jobIds){
        scheduleJobService.resume(jobIds);
        log.info("灰度定时任务成功"+jobIds.toString());
        return ApiResult.success();
    }


}
