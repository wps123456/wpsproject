package com.wps.studyrabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title MessageConsumerSecond
 * @Description
 * @auther wps
 * @Date 2020/7/2217:16
 */
@Component
public class MessageConsumerSecond {

    //监听器监听指定的Queue
    @RabbitListener(queues = "hello")
    public void process(String hello){
        System.out.println("===============消费者2  process========"+hello);

    }
    @RabbitListener(queues = "select")
    public void process2(String hello){
        System.out.println("===============消费者2  process2========"+hello);

    }
}
