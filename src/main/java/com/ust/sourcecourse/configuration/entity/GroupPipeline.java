package com.ust.sourcecourse.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GroupPipeline {
	@Id
	private String uid;
	private String group_uid;
	private boolean exportType;
	private boolean loadType;
	private String recurrance;
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
	public boolean getExportType() {
		return exportType;
	}
	public void setExportType(boolean exportType) {
		this.exportType = exportType;
	}
	public boolean getLoadType() {
		return loadType;
	}
	public void setLoadType(boolean loadType) {
		this.loadType = loadType;
	}
	public String getRecurrance() {
		return recurrance;
	}
	public void setRecurrance(String recurrance) {
		this.recurrance = recurrance;
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
		return "GroupPipeline [uid=" + uid + ", group_uid=" + group_uid + ", exportType=" + exportType + ", loadType="
				+ loadType + ", recurrance=" + recurrance + ", createdBy=" + createdBy + ", created_timestamp="
				+ created_timestamp + ", modifiedBy=" + modifiedBy + ", modified_timestamp=" + modified_timestamp + "]";
	}
	public GroupPipeline(String uid, String group_uid, boolean exportType, boolean loadType, String recurrance,
			String createdBy, String created_timestamp, String modifiedBy, String modified_timestamp) {
		super();
		this.uid = uid;
		this.group_uid = group_uid;
		this.exportType = exportType;
		this.loadType = loadType;
		this.recurrance = recurrance;
		this.createdBy = createdBy;
		this.created_timestamp = created_timestamp;
		this.modifiedBy = modifiedBy;
		this.modified_timestamp = modified_timestamp;
	}
}
