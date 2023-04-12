package com.ust.sourcecourse.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.sourcecourse.configuration.entity.ProjectTable;
import com.ust.sourcecourse.configuration.entity.SourceTable;

@Repository
public interface ProjectTableRepository extends JpaRepository<ProjectTable, Long>{

	List<ProjectTable> findByProjectUid(Long projectId);
}
