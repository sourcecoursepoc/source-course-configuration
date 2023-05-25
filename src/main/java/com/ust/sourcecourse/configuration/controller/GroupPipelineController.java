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
	    public ResponseEntity<GroupPipelineResponse>createPipeline(@PathVariable("groupId")  Long uid,
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
		public ResponseEntity<GroupPipelineResponse> getGroupPipeline(@PathVariable("pipelineId") Long id) {
			GroupPipelineResponse groupPipelineResponse = groupPipelineService.getGroupPipeline(id);
			return ResponseEntity.ok(groupPipelineResponse);
		}
		
		/**
		 * 
		 * @param get pipeline by projectGroup uid
		 * @return
		 */
		@GetMapping("/group/{id}")
		public ResponseEntity<List<GroupPipelineResponse>> PipelinebyProjectGroup(@PathVariable("id") Long uid) {
		    List<GroupPipelineResponse> pipelines = groupPipelineService.findByProjectGroup(uid);
		    if (!pipelines.isEmpty()) {
		        return ResponseEntity.ok(pipelines);
		    } else {
		        return ResponseEntity.noContent().build();
		    }
		}

		
		
		/**
		 * 
		 * @param update by pipeline  id
		 * @return
		 */
		
		@PutMapping("/{id}")
		public ResponseEntity<GroupPipelineResponse> updateGroupPipeline(@PathVariable("id") Long id,
				@RequestBody GroupPipelineRequest groupPipelineRequest) {
			GroupPipelineResponse updatedGroupPipeline = groupPipelineService.updateGroupPipeline(id, groupPipelineRequest);
			return ResponseEntity.ok(updatedGroupPipeline);
		}
		
		/**
		 * 
		 * @param delete by pipeline id
		 * @return
		 */
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteGroupPipeline(@PathVariable("id") Long id) {
			groupPipelineService.deleteGroupPipeline(id);
			return ResponseEntity.noContent().build();
			}
}
