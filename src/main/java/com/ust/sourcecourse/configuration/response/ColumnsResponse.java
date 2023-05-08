package com.ust.sourcecourse.configuration.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColumnsResponse {
	private Long uid;
	private AttributesInfo attributesInfo;
	private SourceInfo sourceInfo;
}