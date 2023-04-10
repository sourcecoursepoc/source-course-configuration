package com.ust.sourcecourse.configuration.entity;

import java.time.LocalDateTime;
import java.util.List;

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
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "project_table")
@Builder
public class ProjectTable {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	@ManyToOne
	@JoinColumn(name = "project_uid", referencedColumnName = "uid")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "source_table_uid", referencedColumnName = "uid")
	private SourceTable sourceTable;

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

	public ProjectTable() {
	}

	public ProjectTable(Long uid, Project project, SourceTable sourceTable, String createdBy,
			LocalDateTime createdTimestamp, String modifiedBy, LocalDateTime modifiedTimestamp) {
		this.uid = uid;
		this.project = project;
		this.sourceTable = sourceTable;
		this.createdBy = createdBy;
		this.createdTimestamp = createdTimestamp;
		this.modifiedBy = modifiedBy;
		this.modifiedTimestamp = modifiedTimestamp;
	}

}
