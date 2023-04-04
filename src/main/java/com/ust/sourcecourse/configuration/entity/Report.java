package com.ust.sourcecourse.configuration.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Report")
public class Report {
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;
	@ManyToOne
	@JoinColumn(name = "consumption_uid", referencedColumnName = "uid")
	private Consumption consumptionUid;
	@ManyToOne
	@JoinColumn(name = "dataQuality_uid", referencedColumnName = "uid")
	private String dataQualityUid;
	@Column(name = "createdBy")
	@CreatedBy
	private String createdBy;
	@Column(name = "created_timestamp")
	@CreationTimestamp
	private Date createdTimestamp;
	@Column(name = "modifiedBy")
	@LastModifiedBy
	private String modifiedBy;
	@Column(name = "modified_timestamp")
	@UpdateTimestamp
	private Date modifiedTimestamp;
}
