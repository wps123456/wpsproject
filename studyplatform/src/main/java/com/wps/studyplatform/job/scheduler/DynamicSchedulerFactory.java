package com.wps.studyplatform.job.scheduler;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Title DynamicSchedulerFactory
 * @Description
 * @auther wps
 * @Date 2020/7/820:19
 */
public class DynamicSchedulerFactory implements InitializingBean {

    private static final Logger LOG =LoggerFactory.getLogger(DynamicSchedulerFactory.class);
    private static Scheduler scheduler;
    public DynamicSchedulerFactory(){

    }
    public static boolean registerJob()





    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
