package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.request.ProjectData;
import com.ust.sourcecourse.configuration.response.ProjectInfo;
import com.ust.sourcecourse.configuration.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping
	@Operation(summary = "Get All Projects", description = "Retrieve all projects")
	public ResponseEntity<List<ProjectInfo>> getAllProject() {
		List<ProjectInfo> projects = projectService.getAllProjects();
		return ResponseEntity.ok(projects);
	}

	@PostMapping
	@Operation(summary = "Create Project", description = "Create a new project")
	public ResponseEntity<ProjectInfo> createProject(@Valid @RequestBody ProjectData projectData) {
		ProjectInfo projectInfo = projectService.createProject(projectData);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(projectInfo);
	}

	@GetMapping("/{project_uid}")
	@Operation(summary = "Get Project by ID", description = "Retrieve project information based on the provided ID")
	public ResponseEntity<ProjectInfo> getProjectById(@PathVariable("project_uid") Long uid) {
		ProjectInfo projectInfo = projectService.getProjectById(uid);

		if (projectInfo != null) {
			return ResponseEntity.ok(projectInfo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{project_uid}")
	public ResponseEntity<String> deleteProject(@PathVariable("project_uid") Long uid) {
		String message = projectService.deleteProject(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).body(message);
	}

	@PutMapping("/{project_uid}")
	public ResponseEntity<ProjectInfo> updateProject(@PathVariable("project_uid") Long uid, @RequestBody ProjectData projectData) {
		ProjectInfo projectInfo = projectService.updateProject(uid, projectData);
		return ResponseEntity.ok(projectInfo);
	}

}
