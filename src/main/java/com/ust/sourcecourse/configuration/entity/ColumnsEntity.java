package com.ust.sourcecourse.configuration.entity;

import java.net.URI;

import org.springframework.web.util.UriComponentsBuilder;

public class ColumnsEntity {
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
	public void setDbid(long id) {
		this.dbid = id;
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
		return "SourcePocEntity [name=" + name + ", notes=" + notes + ", type=" + type + ", isprimary=" + isprimary
				+ ", prefix=" + prefix + ", sufix=" + sufix + ", dbid=" + dbid + ", tableid=" + tableid + ", Columnid="
				+ Columnid + ", Cumulative=" + Cumulative + ", Size=" + Size + ", getName()=" + getName()
				+ ", getNotes()=" + getNotes() + ", getType()=" + getType() + ", isIsprimary()=" + isIsprimary()
				+ ", getPrefix()=" + getPrefix() + ", getSufix()=" + getSufix() + ", getDbid()=" + getDbid()
				+ ", getTableid()=" + getTableid() + ", getColumnid()=" + getColumnid() + ", getCumulative()="
				+ getCumulative() + ", getSize()=" + getSize() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	public static UriComponentsBuilder created(URI location) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}



