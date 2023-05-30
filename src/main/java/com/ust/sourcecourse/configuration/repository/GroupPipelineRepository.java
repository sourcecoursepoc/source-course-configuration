package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.GroupPipeline;
import com.ust.sourcecourse.configuration.entity.ProjectGroup;

public interface GroupPipelineRepository extends JpaRepository<GroupPipeline, Long> {


	GroupPipeline findByProjectGroup(Long uid);

	GroupPipeline findByProjectGroup(ProjectGroup projectGroup);

	

}
