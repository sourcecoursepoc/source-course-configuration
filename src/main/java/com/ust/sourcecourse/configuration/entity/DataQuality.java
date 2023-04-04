package com.ust.sourcecourse.configuration.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
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
@Table(name = "DataQuality")
public class DataQuality {
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;
	@ManyToOne
	@JoinColumn(name = "column_uid", referencedColumnName = "uid")
	private SourceColumns columnUid;
	@Column(name = "score")
	private float score;
	@Column(name = "description")
	private String description;
	@CreatedBy
	@Column(name = "createdBy")
	private String createdBy;
	@CreatedDate
	@Column(name = "created_timestamp")
	private LocalDateTime createdTimestamp;
	@LastModifiedBy
	@Column(name = "modifiedBy")
	private String modifiedBy;
	@Column(name = "modifiedTimestamp")
	@UpdateTimestamp
	private LocalDateTime modifiedTimestamp;
}
