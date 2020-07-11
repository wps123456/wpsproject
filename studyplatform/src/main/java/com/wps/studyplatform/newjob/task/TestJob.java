package com.wps.studyplatform.newjob.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TestJob {
    @Scheduled(cron = "*/5 * * * * ?")
    public void clearLog(){
        System.out.println("执行定时任务");
    }

    public static void main(String[] args) {

    }
}
