package com.ust.sourcecourse.configuration.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectData {

	@NotBlank
	private String name;

	@NotBlank
	private String description;
}
