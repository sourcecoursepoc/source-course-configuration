package com.ust.sourcecourse.configuration.request;

import java.util.List;

import lombok.Data;

@Data
public class TagDescriptionRequest {

	private String description;

	private List<String> Tags;

}
