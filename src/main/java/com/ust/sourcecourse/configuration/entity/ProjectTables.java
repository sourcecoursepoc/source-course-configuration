package com.ust.sourcecourse.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProjectTables {
@Id
	private String uid;
	private String project_uid;
	private String table_uid;
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
	public String getProject_uid() {
		return project_uid;
	}
	public void setProject_uid(String project_uid) {
		this.project_uid = project_uid;
	}
	public String getTable_uid() {
		return table_uid;
	}
	public void setTable_uid(String table_uid) {
		this.table_uid = table_uid;
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
		return "ProjectTables [uid=" + uid + ", project_uid=" + project_uid + ", table_uid=" + table_uid
				+ ", createdBy=" + createdBy + ", created_timestamp=" + created_timestamp + ", modifiedBy=" + modifiedBy
				+ ", modified_timestamp=" + modified_timestamp + "]";
	}
	public ProjectTables(String uid, String project_uid, String table_uid, String createdBy, String created_timestamp,
			String modifiedBy, String modified_timestamp) {
		super();
		this.uid = uid;
		this.project_uid = project_uid;
		this.table_uid = table_uid;
		this.createdBy = createdBy;
		this.created_timestamp = created_timestamp;
		this.modifiedBy = modifiedBy;
		this.modified_timestamp = modified_timestamp;
	}
	
}
