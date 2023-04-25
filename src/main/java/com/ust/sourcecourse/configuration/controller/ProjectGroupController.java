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

import com.ust.sourcecourse.configuration.request.ProjectGroupRequest;
import com.ust.sourcecourse.configuration.response.ProjectGroupResponse;
import com.ust.sourcecourse.configuration.service.ProjectGroupService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/project-groups")
public class ProjectGroupController {

	@Autowired
	private ProjectGroupService projectGroupService;

	/**
	 * 
	 * @param projectGroupRequest
	 * @return
	 */

	@PostMapping
	public ResponseEntity<ProjectGroupResponse> createProjectGroup(
			@Valid @RequestBody ProjectGroupRequest projectGroupRequest) {
		ProjectGroupResponse projectGroupResponse = projectGroupService.createProjectGroup(projectGroupRequest);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(projectGroupResponse);
	}

	/**
	 * 
	 * @param get projectgroup by id
	 * @return
	 */

	@GetMapping("/{groupId}")
	public ResponseEntity<ProjectGroupResponse> getProjectGroup(@PathVariable("groupId") Long id) {
		ProjectGroupResponse projectGroupResponse = projectGroupService.getProjectGroup(id);
		return ResponseEntity.ok(projectGroupResponse);
	}

	/**
	 * 
	 * @param get ProjectGroups by ProjectUid
	 * @return
	 */

	@GetMapping("/project/{projectId}")
	public ResponseEntity<List<ProjectGroupResponse>> getProjectGroupsByProjectUid(
			@PathVariable("projectId") Long uid) {
		List<ProjectGroupResponse> projectGroups = projectGroupService.findByProjectUid(uid);
		if (!projectGroups.isEmpty()) {
			return ResponseEntity.ok(projectGroups);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * 
	 * @param id
	 * @param update projectGroup
	 * @return
	 */

	@PutMapping("/{groupId}")
	public ResponseEntity<ProjectGroupResponse> updateProjectGroup(@PathVariable("groupId") Long id,
			@RequestBody ProjectGroupRequest projectGroupRequest) {
		ProjectGroupResponse updatedProjectGroup = projectGroupService.updateProjectGroup(id, projectGroupRequest);
		return ResponseEntity.ok(updatedProjectGroup);
	}

	/**
	 * 
	 * @param id
	 * @param delete projectGroup
	 * @return
	 */
	@DeleteMapping("/{groupId}")
	public ResponseEntity<Void> deleteProjectGroup(@PathVariable("groupId") Long id) {
		projectGroupService.deleteProjectGroup(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * 
	 * @param tags
	 * @param add  tags to project Group
	 * @return
	 */
	@PostMapping("/{groupId}/tags")
	public ResponseEntity<List<String>> addTagToProjectGroup(@PathVariable("groupId") Long uid,
			@RequestBody List<String> tags) {
		List<String> updatedProjectGroup = projectGroupService.addTagToProjectGroup(uid, tags);
		return ResponseEntity.ok(updatedProjectGroup);
	}

	/**
	 * 
	 * @param
	 * @param delete tag
	 * @return
	 */

	@DeleteMapping("/{groupId}/tags/{tag}")
	public ResponseEntity<String> removeTagFromProjectGroup(@PathVariable("groupId") Long uid, @PathVariable String tag) {
		ResponseEntity<String> updatedProjectGroup = projectGroupService.removeTagFromProjectGroup(uid, tag);
		if (updatedProjectGroup != null) {
			return ResponseEntity.ok("Tag '" + tag + "' deleted successfully from project group with id " + uid);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * 
	 * @param Get Tags by Group
	 * @return Tags
	 */

	@GetMapping("/{groupId}/tags")
	public ResponseEntity<List<String>> getTagsByGroup(@PathVariable("groupId") Long uid) {
		List<String> tags = projectGroupService.getTagsByGroup(uid);
		return ResponseEntity.ok(tags);
	}

	/**
	 * 
	 * @param search Groups By Tag
	 * @return
	 */

	@GetMapping("/searchByTag/{tag}")
	public ResponseEntity<List<ProjectGroupResponse>> searchGroupsByTag(@PathVariable String tag) {
		List<ProjectGroupResponse> groups = projectGroupService.searchGroupsByTag(tag);
		return ResponseEntity.ok(groups);
	}

}