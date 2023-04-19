package com.ust.sourcecourse.configuration.controller;

import java.util.List;

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

import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.request.DBData;
import com.ust.sourcecourse.configuration.request.HomeRequest;
import com.ust.sourcecourse.configuration.response.DBDataSourceInfo;
import com.ust.sourcecourse.configuration.service.DBDataSourceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/db")
public class DBDataSourceController {

	@Autowired
	private DBDataSourceService dataSourceService;

	@PostMapping
	public ResponseEntity<DBDataSourceInfo> saveDB(@Valid @RequestBody DBData dbData) {
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(dataSourceService.saveDB(dbData));
	}

	@DeleteMapping("/{uid}")
	public ResponseEntity<Void> deleteDB(@PathVariable Long uid) {
		dataSourceService.deleteDB(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
	}

	@GetMapping
	public ResponseEntity<List<DBDataSourceInfo>> getDBSourceInfo() {
		return ResponseEntity.ok(dataSourceService.getDBInfo());
	}

	@GetMapping("/tags/columns/{uid}")
	public ResponseEntity<List<String>> getColumnTags(@PathVariable Long uid) {
		List<String> columnTags = dataSourceService.getColumnTags(uid);
		return ResponseEntity.ok(columnTags);
	}

	@GetMapping("/tags/tables/{uid}")
	public ResponseEntity<List<String>> getTableTags(@PathVariable Long uid) {
		List<String> tableTags = dataSourceService.getTableTags(uid);
		return ResponseEntity.ok(tableTags);
	}

	@PutMapping("/tags/columns/{uid}")
	public ResponseEntity<List<String>> updateColumnTags(@PathVariable Long uid, @RequestBody HomeRequest request) {
		SourceColumn column = dataSourceService.updateColumnDescriptionAndTags(uid, request);
		return ResponseEntity.ok(column.getTags());
	}

	@PutMapping("/tags/tables/{uid}")
	public ResponseEntity<List<String>> updateTableTags(@PathVariable Long uid, @RequestBody HomeRequest request) {
		SourceTable table = dataSourceService.updateTableDescriptionAndTags(uid, request);
		return ResponseEntity.ok(table.getTags());
	}

	@DeleteMapping("/tags/columns/{uid}")
	public ResponseEntity<Void> deleteColumnTags(@PathVariable Long uid) {
		dataSourceService.deleteColumnTags(uid);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/tags/tables/{uid}")
	public ResponseEntity<Void> deleteTableTags(@PathVariable Long uid) {
		dataSourceService.deleteTableTags(uid);
		return ResponseEntity.noContent().build();
	}

}
