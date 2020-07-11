package com.wps.studyplatform.newjob.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wps.studyplatform.newjob.entity.TestSysUser;
import com.wps.studyplatform.newjob.service.TestSysUserService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PrintHello implements Job {
    private static final Logger log= LoggerFactory.getLogger(PrintHello.class);
    private static Gson gson=new GsonBuilder().create();

    @Autowired
    private TestSysUserService testSysUserService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("------开始执行定时任务-------");
        TestSysUser testSysUser=testSysUserService.getOne(new QueryWrapper<TestSysUser>().lambda()
                                                   .eq(TestSysUser::getUserId,"1L"));
        log.info("-------任务已执行---------");
        log.info("-------定时任务执行完毕，输出结果为："+gson.toJson(testSysUser));
    }
}
