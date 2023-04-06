package com.ust.sourcecourse.configuration.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DBTableColumnMetadata {

	private String type;
	private boolean isPrimary;
	private boolean isUnique;
	private boolean isNullable;
	private String defaultValue;
}
