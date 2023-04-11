package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.request.DBData;
import com.ust.sourcecourse.configuration.response.ColumnsResponse;
import com.ust.sourcecourse.configuration.response.DBDataSourceInfo;
import com.ust.sourcecourse.configuration.service.ColumnsService;
@RestController
@RequestMapping("/Columns")
public class ColumnsController {
	@Autowired
	
	private ColumnsService columnsService;
	@GetMapping
	public ResponseEntity<List<SourceColumn>> getColumnSourceInfo() {
		return ResponseEntity.ok(columnsService.getColumnInfo());
	}
	@PostMapping("/save")
	public ResponseEntity<?> saveData(@RequestBody SourceColumn dbData) {
		SourceColumn savedata = ColumnsService.saveData(dbData);
		return ResponseEntity.ok(savedata);
		
	}

//	@PostMapping("/PostColumns")s
//	public SourceColumn PostValues(@RequestBody SourceColumn entity ){
//		
//		return ColumnsService.PostColumns(entity);
//		
//		
//		
//	}
	
}
