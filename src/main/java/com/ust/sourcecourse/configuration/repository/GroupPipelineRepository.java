package com.ust.sourcecourse.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.GroupPipeline;
import com.ust.sourcecourse.configuration.entity.ProjectGroup;

public interface GroupPipelineRepository extends JpaRepository<GroupPipeline, Long> {


	List<GroupPipeline> findByProjectGroup(Long uid);

	List<GroupPipeline> findByProjectGroup(ProjectGroup projectGroup);

	

}
