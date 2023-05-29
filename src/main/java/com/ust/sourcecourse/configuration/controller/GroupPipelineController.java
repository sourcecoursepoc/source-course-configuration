package com.ust.sourcecourse.configuration.controller;

import java.util.List;

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
	public ResponseEntity<List<GroupPipelineResponse>> createPipeline(@PathVariable("groupId") Long uid,
			@RequestBody GroupPipelineRequest groupPipelineRequest) throws HttpRequestMethodNotSupportedException {
		List<GroupPipelineResponse> groupPipelineResponse = groupPipelineService.createGroupPipeline(uid,
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
	@GetMapping("/group/{projectGroup_id}")
	@Operation(summary = "Get Pipelines by Project Group", description = "Retrieve pipelines for a specific group identified by group ID")
	public ResponseEntity<List<GroupPipelineResponse>> PipelinebyProjectGroup(@PathVariable("pipeline_id") Long uid) {
		List<GroupPipelineResponse> pipelines = groupPipelineService.findByProjectGroup(uid);
		if (!pipelines.isEmpty()) {
			return ResponseEntity.ok(pipelines);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * 
	 * @param update by pipeline id
	 * @return
	 */

	@PutMapping("/{Pipeline_id}")
	@Operation(summary = "Update Group Pipeline", description = "Update group pipeline for a specific ID")
	public ResponseEntity<GroupPipelineResponse> updateGroupPipeline(@PathVariable("Pipeline_id") Long id,
			@RequestBody GroupPipelineRequest groupPipelineRequest) {
		GroupPipelineResponse updatedGroupPipeline = groupPipelineService.updateGroupPipeline(id, groupPipelineRequest);
		return ResponseEntity.ok(updatedGroupPipeline);
	}

	/**
	 * 
	 * @param delete by pipeline id
	 * @return
	 */

	@DeleteMapping("/{pipeline_id}")
	@Operation(summary = "Delete Group Pipeline", description = "Delete a group pipeline by ID")
	public ResponseEntity<Void> deleteGroupPipeline(@PathVariable("pipeline_id") Long id) {
		groupPipelineService.deleteGroupPipeline(id);
		return ResponseEntity.noContent().build();
	}
}
