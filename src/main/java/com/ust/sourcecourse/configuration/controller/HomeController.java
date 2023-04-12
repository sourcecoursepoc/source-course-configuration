package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class HomeController {
	@GetMapping("/projects/{id}")
	public Project getProjectById(@PathVariable Long id) {
		return projectService.getProjectById(id);
	}
	@GetMapping("/projects")
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}
	}

	





