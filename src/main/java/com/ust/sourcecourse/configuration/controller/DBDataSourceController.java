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

import com.ust.sourcecourse.configuration.request.DBData;
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
	public ResponseEntity<List<DBDataSourceInfo>> getAllDBSourceInfo() {
		return ResponseEntity.ok(dataSourceService.getAllDBInfo());
	}

	@GetMapping("/{uid}")
	public ResponseEntity<DBDataSourceInfo> getDBSourceInfo(@PathVariable Long uid) {
		return ResponseEntity.ok(dataSourceService.getDBInfo(uid));
	}

	@PutMapping("/{uid}")
	public ResponseEntity<DBDataSourceInfo> updateDBSourceInfo(@PathVariable Long uid,
			@Valid @RequestBody DBData dbData) {
		return ResponseEntity.ok(dataSourceService.updateDB(uid, dbData));
	}

}
