package com.ust.sourcecourse.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.request.GroupPipelineRequest;
import com.ust.sourcecourse.configuration.response.GroupPipelineResponse;
import com.ust.sourcecourse.configuration.service.GroupPipelineService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/group-pipeline")
public class GroupPipelineController {

	@Autowired
	private GroupPipelineService groupPipelineService;

	/**
	 * 
	 * @param uid
	 * @param groupPipelineRequest
	 * @return
	 * @throws HttpRequestMethodNotSupportedException
	 */
	@PostMapping("/{groupId}")
	@Operation(summary = "Create Pipeline", description = "Create a pipeline for a specific group by groupId")
	public ResponseEntity<GroupPipelineResponse> createPipeline(@PathVariable("groupId") Long uid,
			@RequestBody GroupPipelineRequest groupPipelineRequest) throws HttpRequestMethodNotSupportedException {
		GroupPipelineResponse groupPipelineResponse = groupPipelineService.createGroupPipeline(uid,
				groupPipelineRequest);
		return ResponseEntity.ok(groupPipelineResponse);
	}

	/**
	 * 
	 *
	 * @param Get by pipeline id
	 * @return
	 */

	@GetMapping("/{pipelineId}")
	@Operation(summary = "Get Group Pipeline", description = "Retrieve a group pipeline by pipeline ID")
	public ResponseEntity<GroupPipelineResponse> getGroupPipeline(@PathVariable("pipelineId") Long id) {
		GroupPipelineResponse groupPipelineResponse = groupPipelineService.getGroupPipeline(id);
		return ResponseEntity.ok(groupPipelineResponse);
	}

	/**
	 * 
	 * @param get pipeline by projectGroup uid
	 * @return
	 */

	@GetMapping("/group/{projectGroupId}")
	@Operation(summary = "Get Pipelines by Project Group", description = "Retrieve pipelines for a specific group identified by group ID")
	public ResponseEntity<GroupPipelineResponse> PipelinebyProjectGroup(@PathVariable("projectGroupId") Long uid) {
		GroupPipelineResponse pipelineResponse = groupPipelineService.findByProjectGroup(uid);
		if (pipelineResponse != null) {
			return ResponseEntity.ok(pipelineResponse);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * 
	 * @param update by pipeline id
	 * @return
	 */

	@PutMapping("/{pipelineId}")
	@Operation(summary = "Update Group Pipeline", description = "Update group pipeline for a specific ID")
	public ResponseEntity<GroupPipelineResponse> updateGroupPipeline(@PathVariable("pipelineId") Long id,
			@RequestBody GroupPipelineRequest groupPipelineRequest) {
		GroupPipelineResponse updatedGroupPipeline = groupPipelineService.updateGroupPipeline(id, groupPipelineRequest);
		return ResponseEntity.ok(updatedGroupPipeline);
	}

	/**
	 * 
	 * @param delete by pipeline id
	 * @return
	 */

	@DeleteMapping("/{pipelineId}")
	public ResponseEntity<String> deleteGroupPipeline(@PathVariable("pipelineId") Long id) {
		groupPipelineService.deleteGroupPipeline(id);
		return ResponseEntity.ok("Group pipeline deleted successfully.");
	}

}
