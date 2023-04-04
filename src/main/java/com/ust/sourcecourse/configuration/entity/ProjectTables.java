package com.ust.sourcecourse.configuration.entity;

import java.sql.Date;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ProjectTable")
public class ProjectTables {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;
	@OneToOne
	@JoinColumn(name = "projects_uid", referencedColumnName = "uid")
	private Projects project;
	@Column(name = "table")
	@JoinColumn(name = "source_uid", referencedColumnName = "uid")
	private Source table;
	@Column(name = "createdBy")
	@CreatedBy
	private String createdBy;
	@Column(name = "createdTimestamp")
	@CreationTimestamp
	private Date createdTimestamp;
	@Column(name = "modifiedBy")
	@LastModifiedBy
	private String modifiedBy;
	@Column(name = "modifiedTimestamp")
	@UpdateTimestamp
	private Date modifiedTimestamp;

}
