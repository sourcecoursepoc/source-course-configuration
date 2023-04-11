
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
import com.ust.sourcecourse.configuration.response.ProjectTableResponse;
import com.ust.sourcecourse.configuration.service.ProjectTableService;
import jakarta.validation.Valid;

@RestController

@RequestMapping("/project-tables")
public class ProjectTableController {

	@Autowired
	private ProjectTableService projectTableService;

	@PostMapping
	public ResponseEntity<List<ProjectTableResponse>> createProjectTable(
			@Valid @RequestBody ProjectTableRequest ProjTableReq) {
		List<ProjectTableResponse> projectTableResponse = projectTableService.createProjectTable(ProjTableReq);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(projectTableResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<ProjectTable>> getProjectTable(@PathVariable Long id) {
		Optional<ProjectTable> sourceTable = projectTableService.getProjectTable(id);
		return ResponseEntity.ok(sourceTable);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Optional<ProjectTable>> updateProjectTable(@PathVariable Long id,
			@RequestBody ProjectTable projectTable) {
		Optional<ProjectTable> updatedSourceTable = projectTableService.updateProjectTable(id, projectTable);
		return ResponseEntity.ok(updatedSourceTable);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProjectTable> deleteProjectTable(@PathVariable("id") Long id) {
		projectTableService.deleteSourceTable(id);
		return ResponseEntity.noContent().build();
	}

}
