package com.ust.sourcecourse.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.request.DBInfo;
import com.ust.sourcecourse.configuration.response.DBDataSourceInfo;
import com.ust.sourcecourse.configuration.service.DBDataSourceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/db")
public class DBDataSourceController {

	@Autowired
	private DBDataSourceService dataSourceService;

	@PostMapping
	public ResponseEntity<DBDataSourceInfo> saveDB(@Valid @RequestBody DBInfo dbInfo) {
		return ResponseEntity.ok(dataSourceService.saveDB(dbInfo));
	}

}
