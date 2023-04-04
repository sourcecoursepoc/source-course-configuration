package com.ust.sourcecourse.configuration.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.transform.Source;

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
@Table(name = "Source_Columns") 
public class SourceColumns { 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid",nullable =false) 
	private long uid; 
	@Column(name = "name",nullable = false) 
	private String name; @Column(name = "description") 
	private String description; 
	@ManyToOne 
	@JoinColumn(name = "table_uid" ,referencedColumnName = "uid") 
	private Source tableUid; 
	@Column(name = "type") 
	private String type; 
	@Column(name = "isPrimary")
	private boolean isPrimary; 
	@Column(name = "isUnique")
	private boolean isUnique; 
	@Column(name = "isNullable")
	private boolean isNullable;
	@Column(name = "defaultValue")
	private String defaultValue; 
	@Column(name = "tag") 
	private List<String> tags; 
	@Column(name = "report_uid") 
	@ManyToOne
	@JoinColumn(name = "report_uid", referencedColumnName = "uid") 
	private Report reportUid;
	@Column(name = "createdBy") 
	@CreatedBy
	private String createdBy;
	@Column(name = "created_timestamp")
	@CreationTimestamp 
	private LocalDateTime createdTimestamp; 
	@Column(name = "modifiedBy")
	@LastModifiedBy 
	private String modifiedBy;
	@Column(name = "modified_timestamp")
	@UpdateTimestamp 
	private LocalDateTime modifiedTimestamp;
	}
	
