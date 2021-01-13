package com.wps.studyhttprequest.http.controller;

import com.wps.studyhttprequest.http.service.HttpControllerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title HttpController
 * @Description
 * @auther wps
 * @Date 2020/12/2821:37
 */

@RestController
@RequestMapping("/http")
public class HttpController {

    private static final Logger logger =LoggerFactory.getLogger(HttpController.class);

    @Autowired
    private HttpControllerService httpControllerService;
    /**
     * 无参数
     */
    @GetMapping("/getName")
    public String getName(){
        logger.info("测试info{}","ceshi ");
        logger.error("错误日志");
        logger.debug("调试日志");
        String text = httpControllerService.getStringText();
        return text;
    }
    /**
     * 有参数请求：POST
     */
    @GetMapping("/getResult")
    public String getResult(){
        String result = httpControllerService.getResult();
        return result;
    }
    /**
     * Get请求，获取String类型，并转成JSON对象，并解析出需要的参数
     */
    @GetMapping("/getHostInfo")
    public String getHostInfo(){
        String result = httpControllerService.getHostInfo();
        return result;
    }
}
