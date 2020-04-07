package com.haiyang.flowablespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

/**
 * FlowableSpringbootApplication
 *
 * @author puhaiyang
 * @date 2018/12/19
 */
@SpringBootApplication
public class FlowableSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableSpringbootApplication.class, args);
        System.out.println("启动成功");

    }

}

