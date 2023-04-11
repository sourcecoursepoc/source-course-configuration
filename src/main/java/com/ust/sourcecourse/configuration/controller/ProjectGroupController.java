package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.request.GroupData;
import com.ust.sourcecourse.configuration.response.GroupInfo;
import com.ust.sourcecourse.configuration.service.ProjectGroupService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/group")
public class ProjectGroupController {

	@Autowired
	private ProjectGroupService projectGroupService;

	@PostMapping("project/{projectUid}")
	public ResponseEntity<GroupInfo> addProjectGroup(@PathVariable Long projectUid,
			@Valid @RequestBody GroupData groupData) {
		GroupInfo groupInfo = projectGroupService.addGroup(projectUid, groupData);
		return ResponseEntity.ok(groupInfo);
	}

	@GetMapping("project/{projectUid}")
	public ResponseEntity<List<GroupInfo>> getGroups(@PathVariable Long projectUid) {
		return ResponseEntity.ok(projectGroupService.getGroups(projectUid));
	}

	@GetMapping("/{groupUid}")
	public ResponseEntity<GroupInfo> getGroup(@PathVariable Long groupUid) {
		GroupInfo groupInfo = projectGroupService.getGroup(groupUid);
		return ResponseEntity.ok(groupInfo);
	}

	@PutMapping("/{groupUid}")
	public ResponseEntity<GroupInfo> updateGroup(@PathVariable Long groupUid, @Valid @RequestBody GroupData groupData) {
		GroupInfo groupInfo = projectGroupService.updateGroup(groupUid, groupData);
		return ResponseEntity.ok(groupInfo);
	}

	@GetMapping("/{groupUid}/tags")
	public ResponseEntity<List<String>> getGroupTags(@PathVariable Long groupUid) {
		return ResponseEntity.ok(projectGroupService.getTags(groupUid));
	}

	@PostMapping("/{groupUid}/tags")
	public ResponseEntity<List<String>> addGroupTags(@PathVariable Long groupUid, @RequestBody List<String> tags) {
		return ResponseEntity.ok(projectGroupService.addTags(groupUid, tags));
	}

	@DeleteMapping("/{groupUid}/tags")
	public ResponseEntity<Void> removeGroupTags(@PathVariable Long groupUid, @RequestBody List<String> tags) {
		projectGroupService.removeTags(groupUid, tags);
		return ResponseEntity.noContent().build();
	}
}
