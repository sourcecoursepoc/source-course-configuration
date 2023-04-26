package com.ust.sourcecourse.configuration.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectTableRequest {

	@NotNull
	private Long projectUid;
	@NotNull
	private List<Long> sourceTableUids;
}
