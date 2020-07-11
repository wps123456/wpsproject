package com.wps.studyplatform.job.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wps.studyplatform.exception.base.BaseException;
import com.wps.studyplatform.job.constants.ScheduleConstants;
import com.wps.studyplatform.job.constants.ScheduleStatus;
import com.wps.studyplatform.job.entity.ScheduleJobEntity;
import com.wps.studyplatform.job.entity.ScheduleJobLogEntity;
import com.wps.studyplatform.job.mapper.ScheduleJobMapper;
import com.wps.studyplatform.job.service.ScheduleJobLogService;
import com.wps.studyplatform.job.service.ScheduleJobService;
import com.wps.studyplatform.job.utils.ScheduleUtils;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJobEntity> implements ScheduleJobService {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveJob(ScheduleJobEntity scheduleJobEntity) {
        if (!checkJobName(scheduleJobEntity.getJobName())){
            throw new BaseException(ScheduleConstants.CHECK_JOB_NAME_REPEAT);
        }
        scheduleJobEntity.setCreateTime(new Date());
        scheduleJobEntity.setStatus(ScheduleStatus.NORMAL.getValue());
        this.save(scheduleJobEntity);
        ScheduleUtils.createScheduleJob(schedulerFactoryBean.getScheduler(),scheduleJobEntity);
    }

    @Override
    public Boolean checkJobName(String jobName) {
        ScheduleJobEntity scheduleJobEntity=this.getOne(new QueryWrapper<ScheduleJobEntity>().lambda()
        .eq(ScheduleJobEntity::getJobName,jobName));
        if (null!=scheduleJobEntity){
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJobEntity scheduleJobEntity) {
        ScheduleUtils.updateScheduleJob(schedulerFactoryBean.getScheduler(),scheduleJobEntity);
        this.updateById(scheduleJobEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId:jobIds){
            ScheduleUtils.deleteScheduleJob(schedulerFactoryBean.getScheduler(),jobId);
        }
        //删除日志
        scheduleJobLogService.remove(new QueryWrapper<ScheduleJobLogEntity>().lambda()
        .in(ScheduleJobLogEntity::getJobId,jobIds));

        //删除数据
        this.removeByIds(Arrays.asList(jobIds));

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for (Long jobId:jobIds){
            ScheduleUtils.run(schedulerFactoryBean.getScheduler(),this.getById(jobId));
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for (Long jobId:jobIds){
            ScheduleUtils.pauseJob(schedulerFactoryBean.getScheduler(),jobId);
        }
        updateBatch(jobIds,ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        for (Long jobId:jobIds){
            ScheduleUtils.resumeJob(schedulerFactoryBean.getScheduler(),jobId);
            updateBatch(jobIds,ScheduleStatus.NORMAL.getValue());
        }

    }


    @Override
    public int updateBatch(Long[] jobIds, int status) {

        Map<String, Object> map=new HashMap<>();
        map.put("list",jobIds);
        map.put("status",status);
        return baseMapper.updateBatch(map);
    }



}
