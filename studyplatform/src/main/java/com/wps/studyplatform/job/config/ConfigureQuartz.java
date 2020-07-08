package com.wps.studyplatform.job.config;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

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
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext){

        AutowiringSpringBeanJobFactory jobFactory=new AutowringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    /**
     * schedulerFactoryBean这个类的作用提供对org.quartz.scheduler的创建于配置，并且胡管理他的生命周期与spring同步
     * org.quartz.scheduler：调度器，所有的调度都有他控制
     * @param dataSource 为schedulerFactory配置数据源
     * @param jobFactory 为schedulerfactory配置JobFactory
     * @return
     */

    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource,JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean factory=new SchedulerFactoryBean();
        //可选QuartzScheduler启动时更新已存在的job，这样就不用每次修改targetObject后删除qrtz_job_details表进行记录
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    /**
     * 从/quartz.properties获取配置信息
     * @return
     * @throws IOException
     */
    private Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean=new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}
