package com.ust.sourcecourse.configuration.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ust.sourcecourse.configuration.entity.Project;

import com.ust.sourcecourse.configuration.request.ProjectData;
import com.ust.sourcecourse.configuration.response.ProjectInfo;
import com.ust.sourcecourse.configuration.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping
	public ResponseEntity<ProjectInfo> createProject(@Valid @RequestBody ProjectData projectData) {
		ProjectInfo projectInfo = projectService.createProject(projectData);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(projectInfo);
	}

	@GetMapping("/{uid}")
	public ResponseEntity<Optional<Project>> getProjectById(@PathVariable Long uid) {
	    Optional<Project> projectInfo = projectService.getProjectById(uid);
	    if (projectInfo!= null) {
	        return ResponseEntity.ok(projectInfo);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}@DeleteMapping("/{uid}")
	public ResponseEntity<Void>deleteProject(@PathVariable Long uid){
		projectService.deleteProject(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
		
	}
	
}


}
