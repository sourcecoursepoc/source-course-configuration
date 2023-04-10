package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.ProjectTable;

public interface ProjectTableRepository extends JpaRepository<ProjectTable, Long>{

}
