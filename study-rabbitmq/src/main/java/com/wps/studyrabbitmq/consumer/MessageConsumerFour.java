package com.wps.studyrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title MessageConsumerFour
 * @Description
 * @auther wps
 * @Date 2020/8/1120:18
 */
@Component
public class MessageConsumerFour {
    @RabbitListener(queues = "topicQueue1")
    public void getTopicMessage1(String message){
        System.out.println("--------------topicQueue1--------------"+message);
    }
    @RabbitListener(queues = "topicQueue2")
    public void getTopicMessage2(String message){
        System.out.println("--------------topicQueue2--------------"+message);
    }
}
