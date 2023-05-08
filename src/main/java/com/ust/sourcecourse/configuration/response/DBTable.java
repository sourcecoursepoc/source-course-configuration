package com.ust.sourcecourse.configuration.response;

import java.util.List;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DBTable {

	private Long uid;
	private String tableName;
	private String description;
	private DBTableMetadata metadata;
	private List<String> tags;
	private List<DBTableColumn> columns;
}

