package com.wps.studyrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title RabbitMqConfig
 * @Description
 * @auther wps
 * @Date 2020/7/1817:43
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public Queue selectQueue(){
        return new Queue("select");
    }
    @Bean
    public Queue deleteQueue(){
        return new Queue("delete");
    }
    @Bean
    public Queue deleteQueue2(){
        return new Queue("delete2");
    }
    @Bean
    public Queue selectQueue2(){
        return new Queue("select2");
    }

    @Bean
    public Queue topicQueue1(){
        return new Queue("topicQueue1");
    }
    @Bean
    public Queue topicQueue2(){
        return new Queue("topicQueue2");
    }


    /**
     * 交换机类型三种
     * Direct Exchange ：直连型交换机，根据消息携带的路由键将消息投递给对应队列。
     * Fanout Exchange：这个交换机在接收到消息后，会直接转发到绑定到它上面的所有队列。
     * Topic Exchange：主题交换机，这个交换机其实跟直连交换机流程差不多，但是它的特点就是在它的路由键和绑定键之间是有规则的。
     * @return
     */

    //声明交换机
    @Bean
    public FanoutExchange  directHelloExchange(){
        return new FanoutExchange ("fanoutExchange");
    }
    @Bean
    public  DirectExchange routingExchange(){
        return new DirectExchange ("routingExchange");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }


    //绑定交换机和队列
    @Bean
    public Binding bindingFanoutQueue1(Queue selectQueue, FanoutExchange directHelloExchange) {
        return BindingBuilder.bind(selectQueue).to(directHelloExchange);
    }
    @Bean
    public Binding bindingFanoutQueue2(Queue deleteQueue, FanoutExchange directHelloExchange) {
        return BindingBuilder.bind(deleteQueue).to(directHelloExchange);
    }
    //routing key模式，交换机---队列---routing key
    @Bean
    public Binding bindingDirectExchange1(Queue selectQueue2, DirectExchange routingExchange) {
        return BindingBuilder.bind(selectQueue2).to(routingExchange).with("selectOne");
    }
    @Bean
    public Binding bindingDirectExchange2(Queue deleteQueue2, DirectExchange routingExchange) {
        return BindingBuilder.bind(deleteQueue2).to(routingExchange).with("selectTwo");
    }
    @Bean
    public Binding bindingDirectExchange3(Queue deleteQueue2, DirectExchange routingExchange) {
        return BindingBuilder.bind(deleteQueue2).to(routingExchange).with("selectThree");
    }
    //topic 模式

    @Bean
    public Binding bindingTopicExchange1(Queue topicQueue1,TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("topic.message");
    }
    @Bean
    public Binding bindingTopicExchange2(Queue topicQueue2,TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("topic.#");
    }
/*
    @Bean
    public Queue userQueue() {
        return new Queue("user");
    }

    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message");
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }
    //===============以上是验证topic Exchange的队列==========


    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }
    //===============以上是验证Fanout Exchange的队列==========


    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    *//**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     *//*
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    *//**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessage
     * @param exchange
     * @return
     *//*
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }*/

}
