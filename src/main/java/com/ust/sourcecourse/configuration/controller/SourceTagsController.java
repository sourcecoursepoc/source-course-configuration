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

import com.ust.sourcecourse.configuration.service.SourceDataService;

@RestController
@RequestMapping("/source/tags")
public class SourceTagsController {

	@Autowired
	private SourceDataService sourceDataService;

	@PostMapping("/table/{sourceTableUid}")
	public ResponseEntity<List<String>> addTableTags(@PathVariable Long sourceTableUid,
			@RequestBody List<String> tags) {
		List<String> tableTags = sourceDataService.addTableTags(sourceTableUid, tags);
		return ResponseEntity.ok(tableTags);
	}

	@DeleteMapping("/table/{sourceTableUid}")
	public ResponseEntity<List<String>> removeTableTags(@PathVariable Long sourceTableUid,
			@RequestBody List<String> tags) {
		List<String> tableTags = sourceDataService.removeTableTags(sourceTableUid, tags);
		return ResponseEntity.ok(tableTags);
	}

	@PostMapping("/column/{sourceColumnUid}")
	public ResponseEntity<List<String>> addColumnTags(@PathVariable Long sourceColumnUid,
			@RequestBody List<String> tags) {
		List<String> tableTags = sourceDataService.addColumnTags(sourceColumnUid, tags);
		return ResponseEntity.ok(tableTags);
	}

	@DeleteMapping("/column/{sourceColumnUid}")
	public ResponseEntity<List<String>> removeColumnTags(@PathVariable Long sourceColumnUid,
			@RequestBody List<String> tags) {
		List<String> tableTags = sourceDataService.removeColumnTags(sourceColumnUid, tags);
		return ResponseEntity.ok(tableTags);
	}
}
