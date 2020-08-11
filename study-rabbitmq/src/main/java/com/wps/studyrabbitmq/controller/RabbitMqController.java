package com.wps.studyrabbitmq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title RabbitMqController
 * @Description
 * @auther wps
 * @Date 2020/7/179:12
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {
    private static final Logger log = LoggerFactory.getLogger(RabbitMqController.class);

    /**
     * Broker：简单来说就是消息队列服务器实体。
     *https://blog.csdn.net/typ1805/article/details/82835318
     * 　　Exchange：消息交换机，它指定消息按什么规则，路由到哪个队列。
     * 　　Queue：消息队列载体，每个消息都会被投入到一个或多个队列。
     * 　　Binding：绑定，它的作用就是把exchange和queue按照路由规则绑定起来。
     * 　　Routing Key：路由关键字，exchange根据这个关键字进行消息投递。
     * 　　vhost：虚拟主机，一个broker里可以开设多个vhost，用作不同用户的权限分离。
     * 　　producer：消息生产者，就是投递消息的程序。
     * 　　consumer：消息消费者，就是接受消息的程序。
     * 　　channel：消息通道，在客户端的每个连接里，可建立多个channel，每个channel代表一个会话任务。
     *消息队列的使用过程大概如下：
     *    客户端连接到消息队列服务器，打开一个channel。
     * 　　客户端声明一个exchange，并设置相关属性。
     * 　　客户端声明一个queue，并设置相关属性。
     * 　　客户端使用routing key，在exchange和queue之间建立好绑定关系。
     * 　　客户端投递消息到exchange。
     */
    @Autowired
    public AmqpTemplate amqpTemplate;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @GetMapping("/send")
    public void sendMessage(){
        String context = "hello " + format.format(new Date());
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("hello", context);
        System.out.println("消息已经发送");
    }

    /**
     *  发布订阅模式
     */
    @GetMapping("/exchangeQueue")
    public void exchangeQueue(){
        String context = "hello " + format.format(new Date());
        System.out.println("Sender : " + context);
        amqpTemplate.convertAndSend("fanoutExchange", "", context);
    }

    /**
     * Routing模式
     * 一个交换机绑定多个队列，每个队列都设置routing key,一个队列可以包含多个routing key
     */
    @GetMapping("/exchangeQueueRouting")
    public void exchangeQueueRouting(){
        String context = "hello " + format.format(new Date());
        System.out.println("Sender : " + context);
        amqpTemplate.convertAndSend("routingExchange", "selectOne", context+"selectOne");
        amqpTemplate.convertAndSend("routingExchange", "selectTwo", context+"selectTwo");
        amqpTemplate.convertAndSend("routingExchange", "selectThree", context+"selectThree");

    }


}
