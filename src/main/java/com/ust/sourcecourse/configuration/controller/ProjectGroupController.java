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
import com.ust.sourcecourse.configuration.entity.ProjectGroup;
import com.ust.sourcecourse.configuration.request.ProjectGroupRequest;
import com.ust.sourcecourse.configuration.response.ProjectGroupResponse;
import com.ust.sourcecourse.configuration.service.ProjectGroupService;

import jakarta.validation.Valid;

@RestController
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
	public ResponseEntity<List<ProjectGroupResponse>> createProjectGroup(
			@Valid @RequestBody ProjectGroupRequest projectGroupRequest) {
		List<ProjectGroupResponse> projectGroupResponse = projectGroupService.createProjectGroup(projectGroupRequest);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(projectGroupResponse);
	}

	/**
	 * 
	 * @param get projectgroup by id
	 * @return
	 */

	@GetMapping("/{id}")
	public ResponseEntity<ProjectGroupResponse> getProjectGroup(@PathVariable("id") Long id) {
		ProjectGroupResponse projectGroupResponse = projectGroupService.getProjectGroup(id);
		return ResponseEntity.ok(projectGroupResponse);
	}

	/**
	 * 
	 * @param get ProjectGroups by ProjectUid
	 * @return
	 */

	@GetMapping("/project/{id}")
	public ResponseEntity<List<ProjectGroupResponse>> getProjectGroupsByProjectUid(@PathVariable("id") Long uid) {
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

	@PutMapping("/{id}")
	public ResponseEntity<ProjectGroupResponse> updateProjectGroup(@PathVariable("id") Long id,
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
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProjectGroup(@PathVariable("id") Long id) {
		projectGroupService.deleteProjectGroup(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * 
	 * @param tags
	 * @param add  tags to project Group
	 * @return
	 */
	@PostMapping("/{id}/tags")
    public ResponseEntity<ProjectGroup> addTagToProjectGroup(@PathVariable ("id") Long uid, @RequestBody  List<String> tags) {
        ProjectGroup updatedProjectGroup = projectGroupService.addTagToProjectGroup(uid, tags);
        return ResponseEntity.ok(updatedProjectGroup);
    }
	/**
	 * 
	 * @param
	 * @param delete tag
	 * @return
	 */
	
	@DeleteMapping("/{id}/tags/{tag}")
	public ResponseEntity<String> removeTagFromProjectGroup(@PathVariable ("id")Long uid, @PathVariable String tag) {
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
	
	@GetMapping("/{id}/tags")
	public ResponseEntity<List<String>> getTagsByGroup(@PathVariable ("id")Long uid) {
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