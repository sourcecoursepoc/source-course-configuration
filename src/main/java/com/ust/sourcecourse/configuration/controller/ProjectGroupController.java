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

import com.ust.sourcecourse.configuration.request.ProjectGroupRequest;
import com.ust.sourcecourse.configuration.response.ProjectGroupResponse;
import com.ust.sourcecourse.configuration.service.ProjectGroupService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/project-groups")
public class ProjectGroupController {

	@Autowired
	private ProjectGroupService projectGroupService;

	@PostMapping
	public ResponseEntity<List<ProjectGroupResponse>> createProGroup(
			@Valid @RequestBody ProjectGroupRequest projectGroupRequest) {
		List<ProjectGroupResponse> projectGroupResponse = projectGroupService.createProGroup(projectGroupRequest);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(projectGroupResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectGroupResponse> getProjectGroup(@PathVariable("id") Long id) {
		ProjectGroupResponse projectGroupResponse = projectGroupService.getProjectGroup(id);
		return ResponseEntity.ok(projectGroupResponse);
	}

	@GetMapping("/project/{id}")
	public ResponseEntity<List<ProjectGroupResponse>> getProjectGroupsByProjectUid(@PathVariable("id") Long uid) {
		List<ProjectGroupResponse> projectGroups = projectGroupService.findByProjectUid(uid);
		if (!projectGroups.isEmpty()) {
			return ResponseEntity.ok(projectGroups);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProjectGroupResponse> updateProjectGroup(@PathVariable("id") Long id,
			@RequestBody ProjectGroupRequest projectGroupRequest) {
		ProjectGroupResponse updatedProjectGroup = projectGroupService.updateProjectGroup(id, projectGroupRequest);
		return ResponseEntity.ok(updatedProjectGroup);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProjectGroup(@PathVariable("id") Long id) {
		projectGroupService.deleteProjectGroup(id);
		return ResponseEntity.noContent().build();
	}
}