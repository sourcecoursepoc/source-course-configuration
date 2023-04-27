package com.ust.sourcecourse.configuration.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupPipelineRequest {
	
	private String loadType;
	private String exportType;
	private String recurrence;
	

}
