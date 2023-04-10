package com.ust.sourcecourse.configuration.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectTableResponse {

	private Long uid;

	private ProjectResponse project;

	private SourceTableResponse sourceTable;

	private String createdBy;

	private LocalDateTime createdTimestamp;

	private String modifiedBy;

	private LocalDateTime modifiedTimestamp;

}
