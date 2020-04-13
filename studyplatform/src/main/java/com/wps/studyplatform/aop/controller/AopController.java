package com.wps.studyplatform.aop.controller;

import com.wps.studyplatform.aop.annotation.Log;
import com.wps.studyplatform.aop.enums.BusinessType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(value = "AOP面对切面",tags = {"AOP面对切面"})
@RestController
@RequestMapping("/aop")
public class AopController {
    private static final Logger log = LoggerFactory.getLogger(AopController.class);

    @ApiOperation("测试切面")
    @GetMapping("/sayHello")
    public String sayHello(){
        System.out.println("sayHello");
        return "hello";
    }
    @ApiOperation("测试自定义注解")
    @GetMapping("/log/{id}")
    @Log(businessType = BusinessType.OTHER,title = "测试自定义注解Log")
    public int logTest(@PathVariable int id){
        System.out.println("测试自定义注解Controller");
        return id;
    }

}
