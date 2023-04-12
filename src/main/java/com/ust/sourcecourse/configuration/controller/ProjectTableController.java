
package com.ust.sourcecourse.configuration.controller;

import java.util.List;
import java.util.Optional;
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
import com.ust.sourcecourse.configuration.entity.ProjectTable;
import com.ust.sourcecourse.configuration.request.ProjectTableRequest;
import com.ust.sourcecourse.configuration.response.DBTable;
import com.ust.sourcecourse.configuration.response.ProjectInfo;

import com.ust.sourcecourse.configuration.service.ProjectTableService;
import jakarta.validation.Valid;

@RestController

@RequestMapping("/project-tables")
public class ProjectTableController {

	@Autowired
	private ProjectTableService projectTableService;

	@PostMapping
	public ResponseEntity<List<DBTable>> createProjectTable(@Valid @RequestBody ProjectTableRequest projTableReq) {
		List<DBTable> dbTables = projectTableService.createProjectTable(projTableReq);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(dbTables);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjectInfo> getProjectTable(@PathVariable Long id) {
		ProjectInfo projInfo = projectTableService.getProjectTables(id);
		return ResponseEntity.ok(projInfo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProjectInfo> updateProjectTable(@PathVariable Long id,
			@RequestBody ProjectTable projectTable) {
		ProjectInfo updatedSourceTable = projectTableService.updateProjectTable(id, projectTable);
		return ResponseEntity.ok(updatedSourceTable);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProjectTable> deleteProjectTable(@PathVariable("id") Long id) {
		projectTableService.deleteSourceTable(id);
		return ResponseEntity.noContent().build();
	}

}
