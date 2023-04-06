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
public class DBTable {

	private Long uid;
	private String tableName;
	private String description;
	private DBTableMetadata metadata;
	private List<String> tags;
	private List<DBTableColumn> columns;
}