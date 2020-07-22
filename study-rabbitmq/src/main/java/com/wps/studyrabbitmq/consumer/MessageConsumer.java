package com.wps.studyrabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title MessageConsumer
 * @Description
 * @auther wps
 * @Date 2020/7/1817:55
 */
@Component
public class MessageConsumer {
    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    //监听器监听指定的Queue
    @RabbitListener(queues = "hello")
    public void process(String hello){
        log.info("Receiver:"+hello);
        System.out.println("==========消费者1  process============="+hello);

    }
    @RabbitListener(queues = "delete")
    public void process2(String hello){
        log.info("Receiver:"+hello);
        System.out.println("==========消费者1  process2============="+hello);

    }


}
