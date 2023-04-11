package com.ust.sourcecourse.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return ProjectInfo.builder().uid(project.getUid()).name(project.getName()).description(project.getDescription())
				.build();
	}

	public List<Project> getAllProjects() {
		  List<Project> projects = projectRepository.findAll();
		return projects;
	}

	
}
