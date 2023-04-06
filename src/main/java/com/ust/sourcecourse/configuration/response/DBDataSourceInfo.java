package com.ust.sourcecourse.configuration.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DBDataSourceInfo {

	private Long uid;
	private String dbName;
	private String description;
	private DBMetadata metadata;
	private List<DBTable> tables;
}
