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

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/db")
public class DBDataSourceController {

	@Autowired
	private DBDataSourceService dataSourceService;

	@PostMapping
	@Operation(summary = "Post Data", description = "Save DB information to get metaData")
	public ResponseEntity<DBDataSourceInfo> saveDB(@Valid @RequestBody DBData dbData) {
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(dataSourceService.saveDB(dbData));
	}

	@DeleteMapping("/{dataSourceId}")
	@Operation(summary = "Delete DB", description = "Delete a database identified by its DataSource ID")
	public ResponseEntity<Void> deleteDB(@PathVariable("dataSourceId") Long uid) {
		dataSourceService.deleteDB(uid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
	}

	@GetMapping
	@Operation(summary = "Get DB Source Info", description = "Retrieve information about DB DataSources")
	public ResponseEntity<List<DBDataSourceInfo>> getDBSourceInfo() {
		return ResponseEntity.ok(dataSourceService.getDBInfo());
	}

	@DeleteMapping("/table/{sourceTableId}/{tag}")
	@Operation(summary = "Delete Tag", description = "Remove a specific tag from a SourceTable identified by its ID")
	public ResponseEntity<String> removeTagFromSourceTable(@PathVariable("sourceTableId") Long uid,
			@PathVariable String tag) {
		ResponseEntity<String> updatedSourceTable = dataSourceService.removeTagFromSourceTable(uid, tag);
		if (updatedSourceTable != null) {
			return ResponseEntity.ok("Tag '" + tag + "' deleted successfully from SourceTable with id " + uid);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/column/{sourceColumnId}/{tag}")
	@Operation(summary = "Delete Tag", description = "Remove a specific tag from a SourceColumn identified by its ID")
	public ResponseEntity<String> removeTagFromSourceColumn(@PathVariable("sourceColumnId") Long uid,
			@PathVariable String tag) {
		ResponseEntity<String> updatedSourceColumn = dataSourceService.removeTagFromSourceColumn(uid, tag);
		if (updatedSourceColumn != null) {
			return ResponseEntity.ok("Tag '" + tag + "' deleted successfully from SourceColumn with id " + uid);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/table/tags/{sourceTableId}")
	@Operation(summary = "Get Tags by Source Table", description = "Retrieve tags for a specific source table identified by its ID")
	public ResponseEntity<List<String>> getTagsBySourceTable(@PathVariable("sourceTableId") Long uid) {
		List<String> tags = dataSourceService.getTagsByTable(uid);
		return ResponseEntity.ok(tags);
	}

	@GetMapping("/column/tags/{sourceColumnId}")
	@Operation(summary = "Get Tags by Source Column", description = "Retrieve tags for a specific source column identified by its ID")
	public ResponseEntity<List<String>> getTagsBySourceColumn(@PathVariable("sourceColumnId") Long uid) {
		List<String> tags = dataSourceService.getTagsByColumn(uid);
		return ResponseEntity.ok(tags);
	}

	@GetMapping("/table/search/{tag}")
	@Operation(summary = "Search Tables", description = "Search database tables by tag")
	public ResponseEntity<List<DBTable>> searchTablesByTag(@PathVariable String tag) {
		List<DBTable> sourcetables = dataSourceService.searchTablesByTag(tag);
		return ResponseEntity.ok(sourcetables);
	}

	@GetMapping("/column/search/{tag}")
	@Operation(summary = "Search Columns", description = "Search database Columns by tag")
	public ResponseEntity<List<DBTableColumn>> searchColumnByTag(@PathVariable String tag) {
		List<DBTableColumn> groups = dataSourceService.searchColumnsByTag(tag);
		return ResponseEntity.ok(groups);
	}

	@PostMapping("/column/{sourceColumnId}")
	@Operation(summary = "Add Tag to Source Column", description = "Add a tag to a source column identified by its ID")
	public ResponseEntity<List<String>> addTagToSourceColumn(@PathVariable("sourceColumnId") Long uid,
			@RequestBody TagDescriptionRequest request) {
		List<String> updatedSourceColumn = dataSourceService.addTagSourceColumn(uid, request.getTags(),
				request.getDescription());
		return ResponseEntity.ok(updatedSourceColumn);
	}

	@PostMapping("/table/{sourceTableId}")
	@Operation(summary = "Add Tag to Source Table", description = "Add a tag to a source table identified by its ID")
	public ResponseEntity<List<String>> addTagToSourceTable(@PathVariable("sourceTableId") Long uid,
			@RequestBody TagDescriptionRequest request) {
		List<String> updatedSourceTable = dataSourceService.addTagSourceTable(uid, request.getTags(),
				request.getDescription());
		return ResponseEntity.ok(updatedSourceTable);
	}

}
