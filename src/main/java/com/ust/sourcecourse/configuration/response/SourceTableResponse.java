package com.ust.sourcecourse.configuration.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SourceTableResponse {
	
	private Long uid;
	private String name;
	private String description;
	private Long dataSourceUid;
    private Long rowCount;
    private String size;
    private LocalDate minDate;
    private LocalDate maxDate;
    private Long yoyCount;
    private Long momCount;
    private List<String> tags;
    private String createdBy;
    private LocalDateTime createdTimestamp;
    private String modifiedBy;
    private LocalDateTime modifiedTimestamp;
    private List<SourceColumnResponse> sourceColumns;
    private List<ProjectTableResponse> projectTables;

}
