package com.wps.studyplatform.kafka.controller;

import com.wps.studyplatform.kafka.model.KafkaSendMessage;
import com.wps.studyplatform.kafka.provider.KafkaSender;
import com.wps.studyplatform.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title KafkaController
 * @Description
 * @auther wps
 * @Date 2020/5/616:26
 */
@Api(value = "kafka消息发送接受测试",tags = {"kafka消息发送接受测试"})
@RequestMapping("/kafka")
@RestController
public class KafkaController {
    @Autowired
    private KafkaSender kafkaSender;
    @ApiOperation(value = "kafka发送消息")
    @GetMapping("/send")
    public ApiResult sendMessage(){
        KafkaSendMessage kafkaSendMessage=kafkaSender.sendMessage();
        return ApiResult.success(kafkaSendMessage);
    }
}
