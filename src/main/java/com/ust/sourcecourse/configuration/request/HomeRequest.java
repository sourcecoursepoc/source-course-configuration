package com.ust.sourcecourse.configuration.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class HomeRequest {
	@NotBlank
	private String description;
	@NotBlank
	private List<String> Tags;


}
