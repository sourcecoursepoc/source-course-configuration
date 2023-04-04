package com.ust.sourcecourse.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DataQuality {
	@Id
	private String uid;
	private String column_uid;
	private String score;
	private String description;
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		return "DataQuality [uid=" + uid + ", column_uid=" + column_uid + ", score=" + score + ", description="
				+ description + ", createdBy=" + createdBy + ", created_timestamp=" + created_timestamp
				+ ", modifiedBy=" + modifiedBy + ", modified_timestamp=" + modified_timestamp + "]";
	}
	public DataQuality(String uid, String column_uid, String score, String description, String createdBy,
			String created_timestamp, String modifiedBy, String modified_timestamp) {
		super();
		this.uid = uid;
		this.column_uid = column_uid;
		this.score = score;
		this.description = description;
		this.createdBy = createdBy;
		this.created_timestamp = created_timestamp;
		this.modifiedBy = modifiedBy;
		this.modified_timestamp = modified_timestamp;
	}
	
}
