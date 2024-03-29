package com.ust.sourcecourse.configuration.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "data_source")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataSource {

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private String status;

	@Column(name = "region")
	private String region;

	@Column(name = "totalTables")
	private Integer totalTables;

	@Column(name = "size")
	private String size;

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

	@OneToOne(mappedBy = "dataSource", cascade = CascadeType.ALL)
	private ConnectionInfo connectionInfo;

	@OneToMany(mappedBy = "dataSource", cascade = CascadeType.ALL)
	@OrderBy("uid")
	private List<SourceTable> sourceTables;

}
