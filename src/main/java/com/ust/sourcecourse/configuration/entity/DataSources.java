package com.ust.sourcecourse.configuration.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "DataSources")
public class DataSources {
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "status")
	private String status;
	@Column(name = "region")
	private String region;
	@Column(name = "totalTables")
	private int totalTables;
	@Column(name = "size")
	private long size;
	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;
	@Column(name = "created_timestamp")
	@CreationTimestamp
	private LocalDateTime createdTimestamp;
	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;
	@Column(name = "modified_timestamp")
	@UpdateTimestamp
	private LocalDateTime modifiedTimestamp;
	
}
