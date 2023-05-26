package com.ust.sourcecourse.configuration.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.constant.AppConstant;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public String producer(Long dataSourceUid) {
		kafkaTemplate.send(AppConstant.KAFKA_METADATA_EVENT_TOPIC, String.valueOf(dataSourceUid));
		log.info(String.format("Message sent -> %s", dataSourceUid));
		return "Message sent to pipeline extractor";
	}

}