package com.ust.sourcecourse.configuration.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.constant.AppConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Async
	public void producer(Long dataSourceUid) {

		CompletableFuture<SendResult<String, String>> future = kafkaTemplate
				.send(AppConstant.KAFKA_METADATA_EVENT_TOPIC, String.valueOf(dataSourceUid));

		future.whenComplete((result, ex) -> {
			if (ex == null) {
				log.info(String.format("Message sent succesfully -> %s", dataSourceUid));
			} else {
				log.error("Error while sending data to Kafka - {}", ex);
			}
		});
	}

}