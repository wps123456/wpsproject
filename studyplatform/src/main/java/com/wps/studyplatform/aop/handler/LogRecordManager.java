package com.wps.studyplatform.aop.handler;

import com.wps.studyplatform.aop.entity.SystemOperLog;
import com.wps.studyplatform.aop.sevice.SystemLogService;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 利用线程的方式记录操作日志
 * 异步线程
 */
public class LogRecordManager {
    private final int OPERATE_DELAY_TIME=10;
    private ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(3,6,60, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(5));
    private static LogRecordManager logRecordManager=null;
    public static synchronized LogRecordManager getInstance(){
        if(logRecordManager==null){
            logRecordManager=new LogRecordManager();
        }
        return logRecordManager;
    }
    public void exec(SystemLogService systemLogService, SystemOperLog systemOperLog){
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //执行具体的保存数据中的代码
                System.out.println("保存数据库成功");

            }
        });
    }
}
