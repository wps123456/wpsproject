package com.wps.studyplatform.kafka.consumer;

import com.wps.studyplatform.kafka.constant.KafkaConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * @Title KafkaConsumer
 * @Description
 * @auther wps
 * @Date 2020/5/616:48
 */
@Component
public class KafkaConsumer {
    private static final Logger log=LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = {KafkaConstant.WPS_KAFKA_ONE})
    public void listen(ConsumerRecord<?,?> record){
        Optional<?> kafkaMessage=Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()){
            log.info("------开始接受kafka消息");
            Object message=kafkaMessage.get();
            log.info("-------record"+record);
            log.info("-------message"+message);
            log.info("------接收到的kafka成功");

        }
    }
}
