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
	public ResponseEntity<DBDataSourceInfo> saveDB(@Valid @RequestBody DBData dbInfo) {
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(dataSourceService.saveDB(dbInfo));
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

}
