package com.ust.sourcecourse.configuration.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ColumnData {

	@NotBlank
	private String name;
	
	private String notes;
	
	private String defaultValue;
	
	private String preffix;
	
	private String suffix;
	
	@NotNull
	private Long sourceColumnUid;
}
