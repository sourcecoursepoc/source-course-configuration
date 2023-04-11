package com.ust.sourcecourse.configuration.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectInfo {

	private Long uid;
	private String name;
	private String description;
	private List<DBTable> tables;
	private List<GroupInfo> groups;
	
}
