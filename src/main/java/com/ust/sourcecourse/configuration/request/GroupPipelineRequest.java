package com.ust.sourcecourse.configuration.request;


import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupPipelineRequest {
	
	private String loadType;
	private String exportType;
	private String recurrence;
	private String exportFileName;
	private Set<String> intimationList;
	private String time;
	private Set<Long> monthlyDays;
	private Set<String> weeklyDays;
	

}
