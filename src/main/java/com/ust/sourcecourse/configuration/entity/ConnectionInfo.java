package com.ust.sourcecourse.configuration.entity;

import javax.sql.DataSource;

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
@Table() 
public class ConnectionInfo { 
	@Id 
	@Column(name = "uid") 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long uid; 
	@OneToOne 
	@JoinColumn(name = "datasource_uid", referencedColumnName = "uid") 
	private DataSource datasourceUid; 
	@Column(name = "connectionURL") 
	private String connectionURL;
	@Column(name = "username") 
	private String username; 
	@Column(name = "password") 
	private String password;
	}
		

