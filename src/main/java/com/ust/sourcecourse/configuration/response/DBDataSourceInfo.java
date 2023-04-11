package com.ust.sourcecourse.configuration.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DBDataSourceInfo {

	private Long uid;
	private String dbName;
	private String description;
	private DBMetadata metadata;
	private List<DBTable> tables;
}
