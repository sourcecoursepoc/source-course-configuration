package com.ust.sourcecourse.configuration.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class TagDescriptionRequest {
	@NotBlank
	private String description;
	
	private List<String> Tags;


}
