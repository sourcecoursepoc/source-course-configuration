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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "connection_info")
@Builder
public class ConnectionInfo {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	@OneToOne
	@JoinColumn(name = "datasource_uid", referencedColumnName = "uid")
	private DataSource dataSource;

	@Column(name = "connectionURL")
	private String connectionURL;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

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