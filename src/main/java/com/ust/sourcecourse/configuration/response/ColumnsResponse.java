package com.ust.sourcecourse.configuration.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColumnsResponse {
	private Long uid;
	private String name;
	private String notes;
	private String type;
	private boolean isprimary;
	private String prefix;
	private String sufix;
	private long dbid;
	private String tableid;
	private String Columnid;
	private String Cumulative;
	private String Size;
}