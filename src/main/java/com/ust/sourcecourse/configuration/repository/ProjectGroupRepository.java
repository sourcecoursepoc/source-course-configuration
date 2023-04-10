package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.ProjectGroup;

public interface ProjectGroupRepository extends JpaRepository<ProjectGroup, Long> {

}
