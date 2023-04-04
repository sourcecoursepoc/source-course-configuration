package com.ust.sourcecourse.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Consumption {
 @Id
	private String uid;
	private String column_uid;
	private String count;
	private String group_uids;
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
	public String getColumn_uid() {
		return column_uid;
	}
	public void setColumn_uid(String column_uid) {
		this.column_uid = column_uid;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getGroup_uids() {
		return group_uids;
	}
	public void setGroup_uids(String group_uids) {
		this.group_uids = group_uids;
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
	
}
