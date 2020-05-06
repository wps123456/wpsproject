package com.wps.studyplatform.kafka.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wps.studyplatform.kafka.constant.KafkaConstant;
import com.wps.studyplatform.kafka.model.KafkaSendMessage;
import com.wps.studyplatform.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Title KafkaSender
 * @Description
 * @auther wps
 * @Date 2020/5/616:28
 */
@Component
public class KafkaSender {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    private static final Logger log=LoggerFactory.getLogger(KafkaSender.class);
    private static Gson gson=new GsonBuilder().create();
    @Autowired
    private IdWorker idWorker;

    public KafkaSendMessage sendMessage(){
        KafkaSendMessage kafkaSendMessage=new KafkaSendMessage();
        kafkaSendMessage.setId(idWorker.nextId());
        kafkaSendMessage.setKey("kafka-key");
        kafkaSendMessage.setDate(new Date());
        log.info("----------开始发送kafka消息------");
        log.info("消息内容为"+gson.toJson(kafkaSendMessage));
        kafkaTemplate.send(KafkaConstant.WPS_KAFKA_ONE,gson.toJson(kafkaSendMessage));
        log.info("-------kafka消息发送完成");
        return kafkaSendMessage;
    }
}
