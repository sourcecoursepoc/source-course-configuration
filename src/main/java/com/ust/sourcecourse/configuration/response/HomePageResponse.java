package com.ust.sourcecourse.configuration.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomePageResponse {

	private String projectName;
	private String description;
	private int tables;
	private int groups;
}
