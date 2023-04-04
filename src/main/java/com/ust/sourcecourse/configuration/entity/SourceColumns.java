package com.ust.sourcecourse.configuration.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
@Entity
public class SourceColumns {
@Id
	private String uid;
	private String name;
	private String description;
	private String table_uid;
	private boolean type;
	private boolean isprimary;
	private String unique;
	private String nullable;
	private boolean defaultValue;
	private String tags;
	private String report_uid;
	private String createdBy;
	private String created_timestamp;
	private String modifiedBy;
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
	public String getTable_uid() {
		return table_uid;
	}
	public void setTable_uid(String table_uid) {
		this.table_uid = table_uid;
	}
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public boolean isIsprimary() {
		return isprimary;
	}
	public void setIsprimary(boolean isprimary) {
		this.isprimary = isprimary;
	}
	public String getUnique() {
		return unique;
	}
	public void setUnique(String unique) {
		this.unique = unique;
	}
	public String getNullable() {
		return nullable;
	}
	public void setNullable(String nullable) {
		this.nullable = nullable;
	}
	public boolean getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getReport_uid() {
		return report_uid;
	}
	public void setReport_uid(String report_uid) {
		this.report_uid = report_uid;
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
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModified_timestamp() {
		return modified_timestamp;
	}
	public void setModified_timestamp(String modified_timestamp) {
		this.modified_timestamp = modified_timestamp;
	}
	@Override
	public String toString() {
		return "SourceColumns [uid=" + uid + ", name=" + name + ", description=" + description + ", table_uid="
				+ table_uid + ", type=" + type + ", isprimary=" + isprimary + ", unique=" + unique + ", nullable="
				+ nullable + ", defaultValue=" + defaultValue + ", tags=" + tags + ", report_uid=" + report_uid
				+ ", createdBy=" + createdBy + ", created_timestamp=" + created_timestamp + ", modifiedBy=" + modifiedBy
				+ ", modified_timestamp=" + modified_timestamp + "]";
	}
	public SourceColumns(String uid, String name, String description, String table_uid, boolean type, boolean isprimary,
			String unique, String nullable, boolean defaultValue, String tags, String report_uid, String createdBy,
			String created_timestamp, String modifiedBy, String modified_timestamp) {
		super();
		this.uid = uid;
		this.name = name;
		this.description = description;
		this.table_uid = table_uid;
		this.type = type;
		this.isprimary = isprimary;
		this.unique = unique;
		this.nullable = nullable;
		this.defaultValue = defaultValue;
		this.tags = tags;
		this.report_uid = report_uid;
		this.createdBy = createdBy;
		this.created_timestamp = created_timestamp;
		this.modifiedBy = modifiedBy;
		this.modified_timestamp = modified_timestamp;
	}
	
	
}
