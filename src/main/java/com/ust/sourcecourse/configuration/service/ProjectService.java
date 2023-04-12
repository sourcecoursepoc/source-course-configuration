package com.ust.sourcecourse.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.request.ProjectData;
import com.ust.sourcecourse.configuration.response.ProjectInfo;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public ProjectInfo createProject(ProjectData projectData) {
		Project project = Project.builder().name(projectData.getName()).description(projectData.getDescription())
				.build();
		project = projectRepository.save(project);
		return getProjectInfo(project);
	}

	private ProjectInfo getProjectInfo(Project project) {
		return ProjectInfo.builder().uid(project.getUid()).name(project.getName()).description(project.getDescription())
				.build();
	}

	public ProjectInfo getProjectById(Long uid) {
		Project project = projectRepository.findById(uid).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with uid " + uid + " not found"));
		return getProjectInfo(project);
	}

	public List<ProjectInfo> getAllProjects() {
		List<Project> projects = projectRepository.findAll();
		return projects.stream().map(project -> getProjectInfo(project)).toList();
	}

	public void deleteProject(Long uid) {
		projectRepository.deleteById(uid);
	}

}
