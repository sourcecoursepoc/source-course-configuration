package com.ust.sourcecourse.configuration.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data; 
@Entity 
@Data 
@Table(name = "Source_Tables")
public class SourceTables {
	@Id
	@Column(name = "uid", nullable = false) 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long uid;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description", columnDefinition = "TEXT") 
	private String description; 
	@OneToMany
	@JoinColumn(name = "datasource_uid", referencedColumnName = "uid")
	private DataSource datasourceUid;
	@Column(name = "rowCount") 
	private long rowCount; 
	@Column(name = "size")
	private long size;
	@Column(name = "minDate") 
	private LocalDate minDate;
	@Column(name = "maxDate") 
	private LocalDate maxDate; 
	@Column(name = "yoyCount") 
	private long yoyCount;
	@Column(name = "momCount")
	private long momCount;
	@ElementCollection 
	private List<String> tags = new ArrayList<>();
	@Column(name = "createdBy")
	@CreatedBy 
	private String createdBy;
	@Column(name = "created_timestamp")
	@CreationTimestamp 
	private LocalDateTime createdTimestamp;
	@Column(name = "modifiedBy", length = 255) 
	@LastModifiedBy 
	private String modifiedBy; 
	@Column(name = "modified_timestamp") 
	@UpdateTimestamp 
	private LocalDateTime modifiedTimestamp; 
	}
