package com.ust.sourcecourse.configuration.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributesInfo {
	private String name;
	private String notes;
	private boolean isPrimary;
	private String Type;
	private String defaultValue;
	private String prefix;
	private String suffix;

}
