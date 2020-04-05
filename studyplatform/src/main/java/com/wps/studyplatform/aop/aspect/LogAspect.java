package com.wps.studyplatform.aop.aspect;

import com.google.common.collect.ImmutableList;
import com.wps.studyplatform.aop.annotation.Log;
import com.wps.studyplatform.aop.entity.SystemOperLog;
import com.wps.studyplatform.aop.handler.LogRecordManager;
import com.wps.studyplatform.aop.sevice.SystemLogService;
import com.wps.studyplatform.aop.utils.JSON;
import com.wps.studyplatform.aop.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private SystemLogService systemLogService;
    /**
     * 切入点
     */
    @Pointcut("@annotation(com.wps.studyplatform.aop.annotation.Log)")
    private void logPointCut(){
    }

    /**
     * 后置拦截
     */
    @AfterReturning(pointcut="logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        System.out.println("前置拦截");
        getHandleLog(joinPoint,null);
    }

    /**
     * 异常拦截
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()",throwing = "e")
    public void doAfter(JoinPoint joinPoint,Exception e){
        getHandleLog(joinPoint,e);
        System.out.println("异常拦截");
    }


    private void getHandleLog(final JoinPoint joinPoint ,final Exception e){
        try {
        //获得注解
        Log controllerLog=getAspectLog(joinPoint);
        if (controllerLog==null){
            return;
        }
        SystemOperLog systemOperLog=new SystemOperLog();
        //封装SystemOperLog类，记录信息到数据库

        //记录设置注解上的参数
         getMethodDescription(controllerLog,systemOperLog);
         systemOperLog.setOperTime(new Date());
        //异步线程记录日志到数据库
        LogRecordManager.getInstance().exec(systemLogService,systemOperLog);
        } catch (Exception exp) {
            e.printStackTrace();
        }
    }

    /**
     * 是否存在注解，如果存在则获取
     */

    private Log getAspectLog(JoinPoint joinPoint){
        Signature signature=joinPoint.getSignature();
        MethodSignature methodSignature=(MethodSignature) signature;
        Method method=methodSignature.getMethod();
        if (method!=null){
            return method.getAnnotation(Log.class);
        }
        return null;
    }
    /**
     * 获得注解上的描述信息
     */
    public void getMethodDescription(Log log, SystemOperLog systemOperLog) throws Exception {
        systemOperLog.setBusinessType(log.businessType().ordinal());
        systemOperLog.setTitle(log.title());
        systemOperLog.setOperatorType(log.operatorType().ordinal());
        //是否将请求参数放进log中
        if(log.isSaveRequestData()){
            setRequestValue(systemOperLog);
        }
    }

    /**
     * 获取请求参数，放进log中
     * @param systemOperLog
     */

    private void setRequestValue(SystemOperLog systemOperLog) throws Exception {
        Map<String ,String[]> map= ServletUtils.getRequest().getParameterMap();
        String params= JSON.marshal(map);
        systemOperLog.setOperParam(StringUtils.substring(params,0,255));
    }

}
