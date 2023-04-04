package com.ust.sourcecourse.configuration.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Projects {
  @Id 
	private String uid;
	private String name;
	private String description;
	private String createdBy;
	private String created_timestamp;
	private String modifiedBy;
	private String modified_timestamp;
	List<SourceColumns> ColumnsList=new ArrayList<>();
	List<SourceTables> TablesList=new ArrayList<>();
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
		return "Projects [uid=" + uid + ", name=" + name + ", description=" + description + ", createdBy=" + createdBy
				+ ", created_timestamp=" + created_timestamp + ", modifiedBy=" + modifiedBy + ", modified_timestamp="
				+ modified_timestamp + "]";
	}
	public Projects(String uid, String name, String description, String createdBy, String created_timestamp,
			String modifiedBy, String modified_timestamp) {
		super();
		this.uid = uid;
		this.name = name;
		this.description = description;
		this.createdBy = createdBy;
		this.created_timestamp = created_timestamp;
		this.modifiedBy = modifiedBy;
		this.modified_timestamp = modified_timestamp;
	}
	
}
