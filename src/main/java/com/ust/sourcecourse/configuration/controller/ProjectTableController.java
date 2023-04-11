package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.response.ProjectInfo;
import com.ust.sourcecourse.configuration.service.ProjectTableService;

@RestController
@RequestMapping("/project-table")
public class ProjectTableController {

	@Autowired
	private ProjectTableService projectTableService;

	@PostMapping("/{projectUid}")
	public ResponseEntity<ProjectInfo> addProjectTables(@PathVariable Long projectUid, @RequestBody List<Long> sourceTableUids) {
		ProjectInfo projectInfo = projectTableService.addTables(projectUid, sourceTableUids);
		return ResponseEntity.ok(projectInfo);
	}

	@DeleteMapping("/{projectUid}")
	public ResponseEntity<Void> removeProjectTables(@PathVariable Long projectUid, @RequestBody List<Long> sourceTableUids) {
		projectTableService.removeTables(projectUid, sourceTableUids);
		return ResponseEntity.noContent().build();
	}
}
