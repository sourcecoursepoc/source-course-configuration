
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.request.ProjectTableRequest;
import com.ust.sourcecourse.configuration.response.DBTable;
import com.ust.sourcecourse.configuration.service.ProjectTableService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
	public ResponseEntity<List<DBTable>> getProjectTable(@PathVariable("id") Long uid) {
		List<DBTable> projInfo = projectTableService.getProjectTables(uid);
		return ResponseEntity.ok(projInfo);
	}


	@DeleteMapping
	public ResponseEntity<List<Long>> deleteProjectTable(@RequestBody ProjectTableRequest request) {
		List<Long> deleteTable = projectTableService.deleteProjectTable(request);
		return ResponseEntity.status(HttpStatus.OK.value()).body(deleteTable);
	}

}
