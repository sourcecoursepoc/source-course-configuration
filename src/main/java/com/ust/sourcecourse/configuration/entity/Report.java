package com.ust.sourcecourse.configuration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Report {
@Id
	private String uid;
	private String consumption_uid;
	private String dataQuality_uid;
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
	public String getConsumption_uid() {
		return consumption_uid;
	}
	public void setConsumption_uid(String consumption_uid) {
		this.consumption_uid = consumption_uid;
	}
	public String getDataQuality_uid() {
		return dataQuality_uid;
	}
	public void setDataQuality_uid(String dataQuality_uid) {
		this.dataQuality_uid = dataQuality_uid;
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
		return "Report [uid=" + uid + ", consumption_uid=" + consumption_uid + ", dataQuality_uid=" + dataQuality_uid
				+ ", createdBy=" + createdBy + ", created_timestamp=" + created_timestamp + ", modifiedBy=" + modifiedBy
				+ ", modified_timestamp=" + modified_timestamp + "]";
	}
	public Report(String uid, String consumption_uid, String dataQuality_uid, String createdBy,
			String created_timestamp, String modifiedBy, String modified_timestamp) {
		super();
		this.uid = uid;
		this.consumption_uid = consumption_uid;
		this.dataQuality_uid = dataQuality_uid;
		this.createdBy = createdBy;
		this.created_timestamp = created_timestamp;
		this.modifiedBy = modifiedBy;
		this.modified_timestamp = modified_timestamp;
	}
	
} 
