package com.ust.sourcecourse.configuration.response;

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

   
}


