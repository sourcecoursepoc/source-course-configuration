package com.ust.sourcecourse.configuration.request;

import lombok.Data;

@Data
public class PipelineData {

	private String exportType;

	private String loadType;

	private String recurrence;
}
