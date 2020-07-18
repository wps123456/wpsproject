package com.wps.studyrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyRabbitmqApplication.class, args);
        System.out.println("rabbitmq项目启动成功");
    }

}
