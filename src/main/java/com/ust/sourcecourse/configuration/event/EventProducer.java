package com.ust.sourcecourse.configuration.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.constant.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void produceDataSourceEvent(Long dataSoureUid) {
		String data = String.valueOf(dataSoureUid);
		kafkaTemplate.send(AppConstant.KAFKA_DATASOURCE_EVENT_TOPIC, data);
		log.info(String.format("Message sent -> %s", data));
	}
}
