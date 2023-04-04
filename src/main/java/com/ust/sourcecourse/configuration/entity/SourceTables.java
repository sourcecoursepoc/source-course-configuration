package com.ust.sourcecourse.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SourceTables {
@Id
	private String uid;
	private String name;
	private String description;
	private String datasource_uid;
	private int rowcount;
	private String size;
	private String mindate;
	private String maxdate;
	private String yoycount;
	private String momCount;
	private String tags;
	private String createdBy;
	private String created_timestamp;
	private String moddifiedBy;
	private String modified_timestamp;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDatasource_uid() {
		return datasource_uid;
	}
	public void setDatasource_uid(String datasource_uid) {
		this.datasource_uid = datasource_uid;
	}
	public int getRowcount() {
		return rowcount;
	}
	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getMindate() {
		return mindate;
	}
	public void setMindate(String mindate) {
		this.mindate = mindate;
	}
	public String getMaxdate() {
		return maxdate;
	}
	public void setMaxdate(String maxdate) {
		this.maxdate = maxdate;
	}
	public String getYoycount() {
		return yoycount;
	}
	public void setYoycount(String yoycount) {
		this.yoycount = yoycount;
	}
	public String getMomCount() {
		return momCount;
	}
	public void setMomCount(String momCount) {
		this.momCount = momCount;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreated_timestamp() {
		return created_timestamp;
	}
	public void setCreated_timestamp(String created_timestamp) {
		this.created_timestamp = created_timestamp;
	}
	public String getModdifiedBy() {
		return moddifiedBy;
	}
	public void setModdifiedBy(String moddifiedBy) {
		this.moddifiedBy = moddifiedBy;
	}
	public String getModified_timestamp() {
		return modified_timestamp;
	}
	public void setModified_timestamp(String modified_timestamp) {
		this.modified_timestamp = modified_timestamp;
	}
	@Override
	public String toString() {
		return "SourceTables [uid=" + uid + ", name=" + name + ", description=" + description + ", datasource_uid="
				+ datasource_uid + ", rowcount=" + rowcount + ", size=" + size + ", mindate=" + mindate + ", maxdate="
				+ maxdate + ", yoycount=" + yoycount + ", momCount=" + momCount + ", tags=" + tags + ", createdBy="
				+ createdBy + ", created_timestamp=" + created_timestamp + ", moddifiedBy=" + moddifiedBy
				+ ", modified_timestamp=" + modified_timestamp + "]";
	}
	public SourceTables(String uid, String name, String description, String datasource_uid, int rowcount, String size,
			String mindate, String maxdate, String yoycount, String momCount, String tags, String createdBy,
			String created_timestamp, String moddifiedBy, String modified_timestamp) {
		super();
		this.uid = uid;
		this.name = name;
		this.description = description;
		this.datasource_uid = datasource_uid;
		this.rowcount = rowcount;
		this.size = size;
		this.mindate = mindate;
		this.maxdate = maxdate;
		this.yoycount = yoycount;
		this.momCount = momCount;
		this.tags = tags;
		this.createdBy = createdBy;
		this.created_timestamp = created_timestamp;
		this.moddifiedBy = moddifiedBy;
		this.modified_timestamp = modified_timestamp;
	}
	
	
}
