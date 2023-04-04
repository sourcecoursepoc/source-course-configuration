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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Group_Pipeline")
public class GroupPipeline {
	@Id
	@Column(name = "uid", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;
	@OneToMany
	@JoinColumn(name = "group_uid", referencedColumnName = "uid")
	private Groups groupUid;
	@Column(name = "exportType")
	private String exportType;
	@Column(name = "loadType")
	private String loadType;
	@Column(name = "recurrance")
	private String recurrence;
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
