package com.ust.sourcecourse.configuration.response;

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
public class DBTableColumnMetadata {

	private String type;
	private boolean isPrimary;
	private boolean isUnique;
	private boolean isNullable;
	private String defaultValue;
}
