package com.ust.sourcecourse.configuration.service;

import java.util.Collections;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.ProjectGroup;
import com.ust.sourcecourse.configuration.repository.ProjectGroupRepository;
import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.request.ProjectGroupRequest;
import com.ust.sourcecourse.configuration.response.ProjectGroupResponse;

@Service
public class ProjectGroupService {

	@Autowired
	private ProjectGroupRepository projectGroupRepository;

	@Autowired
	private ProjectRepository projectRepository;

	/**
	 * 
	 * @param projectGroupRequest
	 * @return
	 */
	public List<ProjectGroupResponse> createProGroup(ProjectGroupRequest projectGroupRequest) {

		Long uid = projectGroupRequest.getProjectUid();
		Project project = projectRepository.findById(uid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Project group with uid " + uid + " not found"));
		ProjectGroup projectgrp = ProjectGroup.builder().name(projectGroupRequest.getName())
				.description(projectGroupRequest.getDescription()).tags(projectGroupRequest.getTags()).project(project)
				.build();

		projectgrp = projectGroupRepository.save(projectgrp);
		return Collections.singletonList(getProjectGroupresponse(projectgrp));
	}

	private ProjectGroupResponse getProjectGroupresponse(ProjectGroup projectgrp) {
		return ProjectGroupResponse.builder().uid(projectgrp.getUid()).name(projectgrp.getName())
				.description(projectgrp.getDescription()).tags(projectgrp.getTags())
				.projectUid(projectgrp.getProject().getUid()).build();
	}

	/**
	 * 
	 * @param find project by id
	 * @return
	 */

	public List<ProjectGroupResponse> findByProjectUid(Long uid) {
		List<ProjectGroup> projectGroups = projectGroupRepository.findByProjectUid(uid);
		return projectGroups.stream().map(projectGroup -> getProjectGroupresponse(projectGroup)).toList();
	}

	/**
	 * 
	 * @param uid
	 * @return
	 */
	public ProjectGroupResponse getProjectGroup(Long uid) {
		ProjectGroup projectGroupResponse = projectGroupRepository.findById(uid).orElse(null);
		if (projectGroupResponse != null) {
			return getProjectGroupresponse(projectGroupResponse);
		} else {
			String errorMessage = "Project group with uid " + uid + " not found";
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
		}
	}

	/**
	 * 
	 * @param uid
	 * @param update Group
	 * @return
	 */

	public ProjectGroupResponse updateProjectGroup(Long uid, ProjectGroupRequest projectGroupRequest) {
		Optional<ProjectGroup> existingProjectGroup = projectGroupRepository.findById(uid);
		if (existingProjectGroup.isPresent()) {
			ProjectGroup projectGroup = existingProjectGroup.get();
			projectGroup.setName(projectGroupRequest.getName());
			projectGroup.setDescription(projectGroupRequest.getDescription());
			projectGroup.setTags(projectGroupRequest.getTags());
			ProjectGroup updatedProjectGroup = projectGroupRepository.save(projectGroup);
			return getProjectGroupresponse(updatedProjectGroup);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project group with uid " + uid + " not found");
		}
	}

	/**
	 * 
	 * @param delete uid
	 * @return
	 */

	public boolean deleteProjectGroup(Long uid) {
		Optional<ProjectGroup> existingProjectGroup = projectGroupRepository.findById(uid);
		if (existingProjectGroup.isPresent()) {
			projectGroupRepository.delete(existingProjectGroup.get());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param tags
	 * @return
	 *//*
		 * 
		 * 
		 * 
		 * public Project addTagToProject(Long projectId, Long tagId) {
		 * Optional<Project> optionalProject = projectRepository.findById(projectId);
		 * Optional<Tag> optionalTag = tagRepository.findById(tagId);
		 * 
		 * if (optionalProject.isPresent() && optionalTag.isPresent()) { Project project
		 * = optionalProject.get(); Tag tag = optionalTag.get();
		 * 
		 * if (!project.getTags().contains(tag)) { project.addTag(tag);
		 * projectRepository.save(project); } return project; } else { return null; } }
		 * 
		 * public Project removeTagFromProject(Long projectId, Long tagId) {
		 * Optional<Project> optionalProject = projectRepository.findById(projectId);
		 * Optional<Tag> optionalTag = tagRepository.findById(tagId);
		 * 
		 * if (optionalProject.isPresent() && optionalTag.isPresent()) { Project project
		 * = optionalProject.get(); Tag tag = optionalTag.get();
		 * 
		 * if (project.getTags().contains(tag)) { project.removeTag(tag);
		 * projectRepository.save(project); } return project; } else { return null; } }
		 */
}
