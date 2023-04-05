package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
