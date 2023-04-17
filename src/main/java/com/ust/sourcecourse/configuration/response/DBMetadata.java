package com.ust.sourcecourse.configuration.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DBMetadata {

	private String status;
	private String region;
	private Integer totalTables;
	private String size;

}
