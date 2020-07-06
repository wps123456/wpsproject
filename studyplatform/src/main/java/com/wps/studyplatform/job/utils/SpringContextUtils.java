package com.wps.studyplatform.job.utils;

/**
 * @Title SpringContextUtils
 * @Description
 * @auther wps
 * @Date 2020/7/620:29
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring context工具类
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext=applicationContext;

    }
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
    public static<T> T getBean(String name,Class<T> requiredType){
        return applicationContext.getBean(name,requiredType);
    }
    /**
     * 获取类型为requireType的对象
     */
    public static <T> T getBean(Class<T> clz) throws BeansException{
        T result=(T) applicationContext.getBean(clz);
        return result;
    }
    public static boolean containsBean(String name){
        return applicationContext.containsBean(name);
    }
    public static boolean isSingleton(String name){
        return applicationContext.isSingleton(name);
    }
    public static Class<? extends Object> getType(String name){
        return applicationContext.getType(name);
    }
}
