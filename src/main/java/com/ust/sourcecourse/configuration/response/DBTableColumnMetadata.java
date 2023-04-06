package com.ust.sourcecourse.configuration.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DBTableColumnMetadata {

	private String type;
	private boolean isPrimary;
	private boolean isUnique;
	private boolean isNullable;
	private String defaultValue;
}
