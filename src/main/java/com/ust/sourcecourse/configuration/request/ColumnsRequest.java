package com.ust.sourcecourse.configuration.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ColumnsRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String notes;

	private String type;
	private boolean isPrimary;
	private String defaultvalue;
	private String preffix;
	private String suffix;
	@NotNull
	private Long sourceColumnUid;

}
