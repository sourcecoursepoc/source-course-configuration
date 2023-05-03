package com.ust.sourcecourse.configuration.request;

import java.time.LocalDate;
import java.util.List;

import com.ust.sourcecourse.configuration.entity.ProjectTable;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SourceTableRequest {

	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotBlank
	private Long dataSourceUid;
	@NotBlank
	private Long rowCount;
	@NotBlank
	private String size;
	@NotBlank
	private LocalDate minDate;
	@NotBlank
	private LocalDate maxDate;
	@NotBlank
	private Long yoyCount;
	@NotBlank
	private Long momCount;
	@NotBlank
	private List<String> tags;
	private List<ProjectTable> projectTables;

}
