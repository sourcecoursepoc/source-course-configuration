package com.ust.sourcecourse.configuration.entity;

import java.time.LocalDateTime;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "GroupColumn")
public class GroupsColumns {
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;
	@OneToMany
	@JoinColumn(name = "group_uid", referencedColumnName = "uid", nullable = false)
	private Groups groupUid;
	@Column(name = "name")
	private String name;
	@Column(name = "notes")
	private String notes;
	@Column(name = "type")
	private String type;
	@Column(name = "isPrimary")
	private boolean isPrimary;
	@Column(name = "uniqueKey")
	private String uniqueKey;
	@Column(name = "nullable")
	private boolean isNullable;
	@Column(name = "defaultValue")
	private String defaultValue;
	@Column(name = "prefix")
	private String prefix;
	@Column(name = "suffix")
	private String suffix;
	@OneToMany
	@JoinColumn(name = "source_column_uid", referencedColumnName = "uid", nullable = false)
	private Source sourceColumnUid;
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
