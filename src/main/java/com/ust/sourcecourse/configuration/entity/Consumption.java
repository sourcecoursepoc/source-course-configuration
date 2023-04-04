package com.ust.sourcecourse.configuration.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.transform.Source;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Consumption")
public class Consumption {
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;
	@OneToOne
	@JoinColumn(name = "SourceColumn_uid", referencedColumnName = "uid")
	private Source columnUid;
	@Column(name = "count")
	private int count;
	@ManyToMany
	@JoinColumn(name = "groupsource_uid", referencedColumnName = "uid")
	private List<Groups> groupUids;
	@Column(name = "createdBy")
	@CreatedDate
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
