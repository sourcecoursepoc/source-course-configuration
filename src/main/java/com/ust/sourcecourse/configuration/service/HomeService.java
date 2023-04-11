package com.ust.sourcecourse.configuration.service;

import java.util.List;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.repository.ProjectRepository;

public class HomeService {

	 private ProjectRepository projectRepository;

	    public void ProjectService(ProjectRepository projectRepository) {
	        this.projectRepository = projectRepository;
	    }

	    public List<Project> getAllProjects() {
	        List<Project> projects = projectRepository.findAll();
	        return projects;
	    }
	}

