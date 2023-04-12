package com.ust.sourcecourse.configuration.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class ColumnsRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	

}
