package com.ust.sourcecourse.configuration.entity;

import java.time.LocalDateTime;
import java.util.List;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "source_column")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceColumn {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "source_table_uid", referencedColumnName = "uid")
	private SourceTable sourceTable;

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

	@ElementCollection
	private List<String> tags;

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

	@OneToOne(mappedBy = "sourceColumn")
	private Report report;

	@OneToMany(mappedBy = "sourceColumn")
	private List<GroupColumn> groupColumns;
}
