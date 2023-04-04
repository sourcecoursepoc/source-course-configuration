package com.ust.sourcecourse.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GroupsColumns {
@Id	
 private String uid;
 private String group_uid;
 private String name;
 private String notes;
 private boolean type;
 private boolean isprimary;
 private String unique;
 private String nullable;
 private boolean defaultValue;
 private String prefix;
 private String suffix;
 private String source_column_uid;
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
public String getGroup_uid() {
	return group_uid;
}
public void setGroup_uid(String group_uid) {
	this.group_uid = group_uid;
}
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
public boolean getType() {
	return type;
}
public void setType(boolean type) {
	this.type = type;
}
public boolean getIsprimary() {
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
public String getPrefix() {
	return prefix;
}
public void setPrefix(String prefix) {
	this.prefix = prefix;
}
public String getSuffix() {
	return suffix;
}
public void setSuffix(String suffix) {
	this.suffix = suffix;
}
public String getSource_column_uid() {
	return source_column_uid;
}
public void setSource_column_uid(String source_column_uid) {
	this.source_column_uid = source_column_uid;
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
	return "GroupsColumns [uid=" + uid + ", group_uid=" + group_uid + ", name=" + name + ", notes=" + notes + ", type="
			+ type + ", isprimary=" + isprimary + ", unique=" + unique + ", nullable=" + nullable + ", defaultValue="
			+ defaultValue + ", prefix=" + prefix + ", suffix=" + suffix + ", source_column_uid=" + source_column_uid
			+ ", createdBy=" + createdBy + ", created_timestamp=" + created_timestamp + ", modifiedBy=" + modifiedBy
			+ ", modified_timestamp=" + modified_timestamp + "]";
}
public GroupsColumns(String uid, String group_uid, String name, String notes, boolean type, boolean isprimary,
		String unique, String nullable, boolean defaultValue, String prefix, String suffix, String source_column_uid,
		String createdBy, String created_timestamp, String modifiedBy, String modified_timestamp) {
	super();
	this.uid = uid;
	this.group_uid = group_uid;
	this.name = name;
	this.notes = notes;
	this.type = type;
	this.isprimary = isprimary;
	this.unique = unique;
	this.nullable = nullable;
	this.defaultValue = defaultValue;
	this.prefix = prefix;
	this.suffix = suffix;
	this.source_column_uid = source_column_uid;
	this.createdBy = createdBy;
	this.created_timestamp = created_timestamp;
	this.modifiedBy = modifiedBy;
	this.modified_timestamp = modified_timestamp;
}
	
}
