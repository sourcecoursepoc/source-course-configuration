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

import com.ust.sourcecourse.configuration.request.DBData;
import com.ust.sourcecourse.configuration.request.TagDescriptionRequest;
import com.ust.sourcecourse.configuration.response.DBDataSourceInfo;
import com.ust.sourcecourse.configuration.response.DBTable;
import com.ust.sourcecourse.configuration.response.DBTableColumn;
import com.ust.sourcecourse.configuration.service.DBDataSourceService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

	@DeleteMapping("/table/{id}/{tag}")
	public ResponseEntity<String> removeTagFromSourceTable(@PathVariable("id") Long uid, @PathVariable String tag) {
		ResponseEntity<String> updatedSourceTable = dataSourceService.removeTagFromSourceTable(uid, tag);
		if (updatedSourceTable != null) {
			return ResponseEntity.ok("Tag '" + tag + "' deleted successfully from SourceTable with id " + uid);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/column/{id}/{tag}")
	public ResponseEntity<String> removeTagFromSourceColumn(@PathVariable("id") Long uid, @PathVariable String tag) {
		ResponseEntity<String> updatedSourceColumn = dataSourceService.removeTagFromSourceColumn(uid, tag);
		if (updatedSourceColumn != null) {
			return ResponseEntity.ok("Tag '" + tag + "' deleted successfully from SourceColumn with id " + uid);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/table/tags/{id}")
	public ResponseEntity<List<String>> getTagsBySourceTable(@PathVariable("id") Long uid) {
		List<String> tags = dataSourceService.getTagsByTable(uid);
		return ResponseEntity.ok(tags);
	}

	@GetMapping("/column/tags/{id}")
	public ResponseEntity<List<String>> getTagsBySourceColumn(@PathVariable("id") Long uid) {
		List<String> tags = dataSourceService.getTagsByColumn(uid);
		return ResponseEntity.ok(tags);
	}

	@GetMapping("/table/search/{tag}")
	public ResponseEntity<List<DBTable>> searchTablesByTag(@PathVariable String tag) {
		List<DBTable> sourcetables = dataSourceService.searchTablesByTag(tag);
		return ResponseEntity.ok(sourcetables);
	}

	@GetMapping("/column/search/{tag}")
	public ResponseEntity<List<DBTableColumn>> searchColumnByTag(@PathVariable String tag) {
		List<DBTableColumn> groups = dataSourceService.searchColumnsByTag(tag);
		return ResponseEntity.ok(groups);
	}

	@PostMapping("/column/{id}")
	public ResponseEntity<List<String>> addTagToSourceColumn(@PathVariable("id") Long uid,
			@RequestBody TagDescriptionRequest request) {
		List<String> updatedSourceColumn = dataSourceService.addTagSourceColumn(uid, request.getTags(),
				request.getDescription());
		return ResponseEntity.ok(updatedSourceColumn);
	}

	@PostMapping("/table/{id}")
	public ResponseEntity<List<String>> addTagToSourceTable(@PathVariable("id") Long uid,
			@RequestBody TagDescriptionRequest request) {
		List<String> updatedSourceTable = dataSourceService.addTagSourceTable(uid, request.getTags(),
				request.getDescription());
		return ResponseEntity.ok(updatedSourceTable);
	}

	@GetMapping("/searchByTag/{tag}")
	public ResponseEntity<List<DBTable>> searchTablesByTag1(@PathVariable String tag) {
		List<DBTable> tables = dataSourceService.searchTablesByTag(tag);
		return ResponseEntity.ok(tables);
	}

	@GetMapping("/searchByTag/{tag}")
	public ResponseEntity<List<DBTableColumn>> searchColumnsByTag1(@PathVariable String tag) {
		List<DBTableColumn> columns = dataSourceService.searchColumnsByTag(tag);
		return ResponseEntity.ok(columns);
	}

}
