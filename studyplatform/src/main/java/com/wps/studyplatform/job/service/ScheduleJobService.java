package com.wps.studyplatform.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wps.studyplatform.job.entity.ScheduleJobEntity;
import org.springframework.stereotype.Component;


public interface ScheduleJobService extends IService<ScheduleJobEntity> {

    /**
     * 保存定时任务
     * @param scheduleJobEntity
     */

    void saveJob(ScheduleJobEntity scheduleJobEntity);


    Boolean checkJobName(String jobName);

    /**
     * 更新定时任务
     * @param scheduleJobEntity
     */
    void update(ScheduleJobEntity scheduleJobEntity);

    /**
     * 批量删除定时任务
     * @param jobIds
     */
    void deleteBatch(Long[] jobIds);
    /**
     * 批量更新定时任务状态
     */
    int updateBatch(Long[] jobIds,int status);

    /**
     * 立即执行
     * @param jobIds
     */
    void run(Long[] jobIds);

    /**
     * 暂停定时任务
     * @param jobIds
     */
    void pause(Long[] jobIds);

    /**恢复运行
     *
     * @param jobIds
     */
    void resume(Long[] jobIds);
}
