package com.ust.sourcecourse.configuration.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import jakarta.persistence.Table;
import lombok.Data; 
@Entity 
@Data
@Table(name = "GroupSource") 
public class Groups { 
@Id 
@Column(name = "uid") 
@GeneratedValue(strategy = GenerationType.IDENTITY) 
private long uid; 
@Column(name="name") 
private String name; 
@Column(name="description") 
private String description ; 
@ElementCollection 
private List<String> tags = new ArrayList<>();
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
	