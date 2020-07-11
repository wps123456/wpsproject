package com.wps.studyplatform.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wps.studyplatform.job.entity.ScheduleJobEntity;

import java.util.Map;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {

    /**
     * 批量更新状态
     * @param map
     * @return
     */

    int updateBatch(Map<String, Object> map);
}
