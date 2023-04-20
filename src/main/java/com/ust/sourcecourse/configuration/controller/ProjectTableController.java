
package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ust.sourcecourse.configuration.excpection.ResourceNotFoundException;
import com.ust.sourcecourse.configuration.request.ProjectTableRequest;
import com.ust.sourcecourse.configuration.response.DBTable;
import com.ust.sourcecourse.configuration.service.ProjectTableService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/project-tables")
public class ProjectTableController {

	@Autowired
	private ProjectTableService projectTableService;
	/**
	 * 
	 * @param projTableReq
	 * @return
	 */

	@PostMapping
	public ResponseEntity<List<DBTable>> createProjectTable(@Valid @RequestBody ProjectTableRequest projTableReq) {
		List<DBTable> dbTables = null;
		try {
			dbTables = projectTableService.createProjectTable(projTableReq);
		} catch (ResponseStatusException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"An error occurred while processing the request", ex);
		}
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(dbTables);
	}

	/**
	 * 
	 * @param uid
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<List<DBTable>> getProjectTable(@PathVariable("id") Long uid) {
		try {
			List<DBTable> projInfo = projectTableService.getProjectTables(uid);
			return ResponseEntity.ok(projInfo);
		} catch (IllegalArgumentException ex) {
			throw ex;
		}
	}
	/**
	 * 
	 * @param request
	 * @return
	 */

	@DeleteMapping
	public ResponseEntity<List<Long>> deleteProjectTable(@RequestBody ProjectTableRequest request) {
		try {
			List<Long> deleteTable = projectTableService.deleteProjectTable(request);
			return ResponseEntity.status(HttpStatus.OK.value()).body(deleteTable);
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}

}
