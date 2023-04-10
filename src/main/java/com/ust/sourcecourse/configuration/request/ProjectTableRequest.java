package com.ust.sourcecourse.configuration.request;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectTableRequest {

	private Long projectUid;
	private List<Long> sourceTableUids;
	private String createdBy;
	private String modifiedBy;

}
