package com.ust.sourcecourse.configuration.controller;

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

import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.request.SourceTableRequest;
import com.ust.sourcecourse.configuration.response.SourceTableResponse;
import com.ust.sourcecourse.configuration.service.SourceTableService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/source-tables")
public class SourceTableController {

	
	@Autowired
	private SourceTableService sourceTableService;
	
	@PostMapping
	public ResponseEntity<SourceTableResponse> createSourceTable(@Valid @RequestBody SourceTableRequest tableRequest) {
		SourceTableResponse tableResponse = sourceTableService.createSourceTable(tableRequest);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(tableResponse);
	}

	/*
	 * @PostMapping public ResponseEntity<SourceTable>
	 * createSourceTable(@RequestBody SourceTableRequest sourceTableReq) {
	 * SourceTable savedSourceTable =
	 * sourceTableService.createSourceTable(sourceTableReq); return new
	 * ResponseEntity<>(savedSourceTable, HttpStatus.CREATED); }
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Optional<SourceTable>> getSourceTableById(@PathVariable Long id) {
		Optional<SourceTable> sourceTable = sourceTableService.getSourceTableById(id);
		return new ResponseEntity<>(sourceTable, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Optional<SourceTable>> updateSourceTable(@PathVariable Long id,
			@RequestBody SourceTable sourceTable) {
		Optional<SourceTable> updatedSourceTable = sourceTableService.updateSourceTable(id, sourceTable);
		return new ResponseEntity<>(updatedSourceTable, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSourceTable(@PathVariable("id") Long id) {
		sourceTableService.deleteSourceTable(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
