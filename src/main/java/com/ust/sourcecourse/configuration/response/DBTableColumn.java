package com.ust.sourcecourse.configuration.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DBTableColumn {

	private Long uid;
	private String name;
	private String description;
	private DBTableColumnMetadata metadata;
	private List<String> tags;
}
