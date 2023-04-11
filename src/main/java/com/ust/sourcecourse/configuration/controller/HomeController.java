package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class HomeController {
	@Autowired
	 private ProjectService projectService;

	    public void ProjectController(ProjectService projectService) {
	        this.projectService = projectService;
	    }

	    @GetMapping
	    public ResponseEntity<List<Project>> getAllProjects() {
	        List<Project> projects = projectService.getAllProjects();
	        return ResponseEntity.ok(projects);
	    }

}