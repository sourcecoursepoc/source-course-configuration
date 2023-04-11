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
public class ColumnInfo {

	private Long uid;
	
	private AttributesInfo attributesInfo;

	private DBTableColumn sourceColumn;
}
