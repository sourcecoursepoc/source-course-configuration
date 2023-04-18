package com.ust.sourcecourse.configuration.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectGroupRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String description;

	@NotNull
	private Long projectUid;

	private List<String> tags;

}
