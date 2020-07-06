package com.wps.studyplatform.job.mapper;

/**
 * @Title ScheduleJobLogMapper
 * @Description
 * @auther wps
 * @Date 2020/7/620:24
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wps.studyplatform.job.entity.ScheduleJobLogEntity;
import org.springframework.stereotype.Repository;

/**
 * 定时任务日志
 */
@Repository
public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLogEntity> {
}
