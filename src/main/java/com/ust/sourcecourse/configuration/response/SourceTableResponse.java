package com.ust.sourcecourse.configuration.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SourceTableResponse {
	
	private Long uid;
	private String name;
	private String description;

}
