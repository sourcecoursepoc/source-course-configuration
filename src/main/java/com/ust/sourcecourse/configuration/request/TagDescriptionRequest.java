package com.ust.sourcecourse.configuration.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDescriptionRequest {

	private String description;

	private List<String> tags;

}
