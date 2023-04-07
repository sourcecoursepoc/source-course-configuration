package com.ust.sourcecourse.configuration.response;

public class ColumnsResponse {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isIsprimary() {
		return isprimary;
	}
	public void setIsprimary(boolean isprimary) {
		this.isprimary = isprimary;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSufix() {
		return sufix;
	}
	public void setSufix(String sufix) {
		this.sufix = sufix;
	}
	public long getDbid() {
		return dbid;
	}
	public void setDbid(long dbid) {
		this.dbid = dbid;
	}
	public String getTableid() {
		return tableid;
	}
	public void setTableid(String tableid) {
		this.tableid = tableid;
	}
	public String getColumnid() {
		return Columnid;
	}
	public void setColumnid(String columnid) {
		Columnid = columnid;
	}
	public String getCumulative() {
		return Cumulative;
	}
	public void setCumulative(String cumulative) {
		Cumulative = cumulative;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	@Override
	public String toString() {
		return "ColumnsResponse [name=" + name + ", notes=" + notes + ", type=" + type + ", isprimary=" + isprimary
				+ ", prefix=" + prefix + ", sufix=" + sufix + ", dbid=" + dbid + ", tableid=" + tableid + ", Columnid="
				+ Columnid + ", Cumulative=" + Cumulative + ", Size=" + Size + "]";
	}
	public ColumnsResponse(String name, String notes, String type, boolean isprimary, String prefix, String sufix,
			long dbid, String tableid, String columnid, String cumulative, String size) {
		super();
		this.name = name;
		this.notes = notes;
		this.type = type;
		this.isprimary = isprimary;
		this.prefix = prefix;
		this.sufix = sufix;
		this.dbid = dbid;
		this.tableid = tableid;
		Columnid = columnid;
		Cumulative = cumulative;
		Size = size;
	}
	

}
