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
public class DBTableColumn {

	private Long uid;
	private String name;
	private String description;
	private DBTableColumnMetadata metadata;
	private List<String> tags;
}
