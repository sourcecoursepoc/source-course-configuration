package com.ust.sourcecourse.configuration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	Project findByUid(Long projectUid);

	Optional<Project> findByName(String name);

}
