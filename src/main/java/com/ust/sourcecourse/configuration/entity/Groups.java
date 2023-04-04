package com.ust.sourcecourse.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Groups {
	@Id
	private String uid;
	private String name;
	private String description;
	private String tags;
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
		return "Groups [uid=" + uid + ", name=" + name + ", description=" + description + ", tags=" + tags
				+ ", createdBy=" + createdBy + ", created_timestamp=" + created_timestamp + ", modifiedBy=" + modifiedBy
				+ ", modified_timestamp=" + modified_timestamp + "]";
	}
	public Groups(String uid, String name, String description, String tags, String createdBy, String created_timestamp,
			String modifiedBy, String modified_timestamp) {
		super();
		this.uid = uid;
		this.name = name;
		this.description = description;
		this.tags = tags;
		this.createdBy = createdBy;
		this.created_timestamp = created_timestamp;
		this.modifiedBy = modifiedBy;
		this.modified_timestamp = modified_timestamp;
	}
	
}
