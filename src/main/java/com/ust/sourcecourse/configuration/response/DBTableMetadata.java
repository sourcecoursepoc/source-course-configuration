package com.ust.sourcecourse.configuration.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DBTableMetadata {

	private Long rowCount;
	private String size;
	private LocalDate minDate;
	private LocalDate maxDate;
	private Long yoyCount;
	private Long momCount;
}
