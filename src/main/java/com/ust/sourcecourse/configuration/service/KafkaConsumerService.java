package com.ust.sourcecourse.configuration.service;


import com.ust.sourcecourse.configuration.constant.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = AppConstant.KAFKA_PIPELINE_EVENT_TOPIC,
            groupId = AppConstant.KAFKA_GROUP_ID)
    public void consume(String message){
        log.info(String.format("Message received -> %s", message));
    }
}
