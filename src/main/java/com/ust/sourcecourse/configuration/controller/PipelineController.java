package com.ust.sourcecourse.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.request.PipelineData;
import com.ust.sourcecourse.configuration.response.PipelineInfo;
import com.ust.sourcecourse.configuration.service.PipelineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pipeline")
public class PipelineController {

	@Autowired
	private PipelineService pipelineService;

	@GetMapping("/{pipelineUid}")
	public ResponseEntity<PipelineInfo> getPipeline(@PathVariable Long pipelineUid) {
		PipelineInfo pipelineInfo = pipelineService.getPipelineById(pipelineUid);
		return ResponseEntity.ok(pipelineInfo);
	}

	@PutMapping("/{pipelineUid}")
	public ResponseEntity<PipelineInfo> updatePipeline(@PathVariable Long pipelineUid,
			@Valid @RequestBody PipelineData pipelineData) {
		PipelineInfo pipelineInfo = pipelineService.updatePipeline(pipelineUid, pipelineData);
		return ResponseEntity.ok(pipelineInfo);
	}

	@GetMapping("/group/{groupUid}")
	public ResponseEntity<PipelineInfo> getGroupPipeline(@PathVariable Long groupUid) {
		PipelineInfo pipelineInfo = pipelineService.getPipelineByGroup(groupUid);
		return ResponseEntity.ok(pipelineInfo);
	}

	@PostMapping("/group/{groupUid}")
	public ResponseEntity<PipelineInfo> addPipeline(@PathVariable Long groupUid,
			@Valid @RequestBody PipelineData pipelineData) {
		PipelineInfo pipelineInfo = pipelineService.setPipeline(groupUid, pipelineData);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(pipelineInfo);
	}

	@PutMapping("/group/{groupUid}")
	public ResponseEntity<PipelineInfo> updateGroupPipeline(@PathVariable Long groupUid,
			@Valid @RequestBody PipelineData pipelineData) {
		PipelineInfo pipelineInfo = pipelineService.setPipeline(groupUid, pipelineData);
		return ResponseEntity.ok(pipelineInfo);
	}
}
