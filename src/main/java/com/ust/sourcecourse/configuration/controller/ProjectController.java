package com.ust.sourcecourse.configuration.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping
	public ResponseEntity<List<ProjectInfo>> getAllProject() {
		List<ProjectInfo> projects = projectService.getAllProjects();
		return ResponseEntity.ok(projects);
	}

	@PostMapping
	public ResponseEntity<ProjectInfo> createProject(@Valid @RequestBody ProjectData projectData) {
		ProjectInfo projectInfo = projectService.createProject(projectData);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(projectInfo);
	}

	@GetMapping("/{uid}")
	public ResponseEntity<ProjectInfo> getProjectById(@PathVariable Long uid) {
		ProjectInfo projectInfo = projectService.getProjectById(uid);

		if (projectInfo != null) {
			return ResponseEntity.ok(projectInfo);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{uid}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long uid) {
		projectService.deleteProject(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();

	}
	@PutMapping("/{uid}")
    public ResponseEntity<ProjectInfo> updateProject(@PathVariable Long uid, @RequestBody ProjectData projectData) {
		ProjectInfo projectInfo  = projectService.updateProject(uid, projectData);
      return ResponseEntity.ok(projectInfo);
    }

}
