package com.ust.sourcecourse.configuration.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupPipelineResponse {

    private Long uid;
    private Long groupUid; 
    private String exportType;
    private String loadType;
    private String recurrence;
    private String exportFileName;
	private Set<String> intimationList;
	private String time;
	private Set<Long> monthlyDays;
	private Set<String> weeklyDays;

   
}


