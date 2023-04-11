package com.ust.sourcecourse.configuration.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GroupData {

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	private List<String> tags;
	
	private List<ColumnData> columns;
	
	private PipelineData pipeline;
}
