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
public class DBDataSourceInfo {

	private Long uid;
	private String dbName;
	private String description;
	private DBMetadata metadata;
	private List<DBTable> tables;
}
