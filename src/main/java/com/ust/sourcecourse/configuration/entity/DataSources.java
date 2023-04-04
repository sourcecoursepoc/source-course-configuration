package com.ust.sourcecourse.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DataSources {
@Id
	private String uid;
	private String name;
	private String description;
	private String status;
	private String region;
	private String totalTables;
	private int size;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTotalTables() {
		return totalTables;
	}
	public void setTotalTables(String totalTables) {
		this.totalTables = totalTables;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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
		return "DataSources [uid=" + uid + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", region=" + region + ", totalTables=" + totalTables + ", size=" + size + ", createdBy=" + createdBy
				+ ", created_timestamp=" + created_timestamp + ", modifiedBy=" + modifiedBy + ", modified_timestamp="
				+ modified_timestamp + "]";
	}
	public DataSources(String uid, String name, String description, String status, String region, String totalTables,
			int size, String createdBy, String created_timestamp, String modifiedBy, String modified_timestamp) {
		super();
		this.uid = uid;
		this.name = name;
		this.description = description;
		this.status = status;
		this.region = region;
		this.totalTables = totalTables;
		this.size = size;
		this.createdBy = createdBy;
		this.created_timestamp = created_timestamp;
		this.modifiedBy = modifiedBy;
		this.modified_timestamp = modified_timestamp;
	}
	
}
