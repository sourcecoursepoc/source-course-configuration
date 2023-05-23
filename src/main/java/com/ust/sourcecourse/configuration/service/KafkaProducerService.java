package com.ust.sourcecourse.configuration.service;


import jakarta.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.constant.AppConstant;



@Slf4j
@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public String producer(Long dataSourceUid) {
		kafkaTemplate.send(AppConstant.KAFKA_METADATA_EVENT_TOPIC, String.valueOf(dataSourceUid));
		log.info(String.format("Message received -> %s", dataSourceUid));
		return "Message sent to pipeline extractor";
	}

}