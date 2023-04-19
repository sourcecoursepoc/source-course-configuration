package com.ust.sourcecourse.configuration.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.ProjectGroup;

public interface ProjectGroupRepository extends JpaRepository<ProjectGroup, Long> {

	List<ProjectGroup> findByProjectUid(Long uid);

	List<ProjectGroup> findAllByTagsIn(List<String> tags);

	Optional<ProjectGroup> findByTags(String tags);


}
