package com.wps.studyplatform.aop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/aop")
public class AopController {
    private static final Logger log = LoggerFactory.getLogger(AopController.class);

    @GetMapping("/sayHello")
    public String sayHello(){
        return "hello";
    }

}
