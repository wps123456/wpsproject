package com.wps.studyplatform.job.config;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

/**
 * @Title ConfigureQuartz
 * @Description
 * @auther wps
 * @Date 2020/7/720:20
 */
public class ConfigureQuartz {
    @Value("${file.path}")
    private String basePath;
    //配置JobFactory
    public JobFactory jobFactory(ApplicationContext applicationContext){

    }
}
