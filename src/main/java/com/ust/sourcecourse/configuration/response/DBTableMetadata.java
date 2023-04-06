package com.ust.sourcecourse.configuration.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DBTableMetadata {

	private Long rowCount;
	private String size;
	private LocalDate minDate;
	private LocalDate maxDate;
	private Long yoyCount;
	private Long momCount;
}