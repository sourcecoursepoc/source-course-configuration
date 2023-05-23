package com.ust.sourcecourse.configuration.entity;

public class KafkaEvent {
	
	private EventType eventType;
	private String data;

}
 enum EventType{
	createdDate,
	createdTime
}