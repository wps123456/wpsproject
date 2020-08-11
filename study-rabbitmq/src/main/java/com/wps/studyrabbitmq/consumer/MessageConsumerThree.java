package com.wps.studyrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title MessageConsumerThree
 * @Description
 * @auther wps
 * @Date 2020/7/2720:26
 */
@Component
public class MessageConsumerThree {
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "select2"), exchange = @Exchange(value = "routingExchange"), key = "selectOne")})
    public void getSelectOne(String hello) throws Exception {
        System.out.println("------------selectOne接受消息成功---------" + hello);
    }
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "select2"), exchange = @Exchange(value = "routingExchange"), key = "selectTwo")})
    public void getSelectTwo(String hello) throws Exception {
        System.out.println("------------selectTwo接受消息成功---------" + hello);
    }

}
