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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.request.ColumnsRequest;
import com.ust.sourcecourse.configuration.response.ColumnsResponse;
import com.ust.sourcecourse.configuration.service.ColumnsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/columns")
public class ColumnsController {
	@Autowired

	private ColumnsService columnsService;

	@GetMapping("/{groupId}")
	@Operation(summary = "Get Column Info", description = "Retrieve the column information for a specific group")
	public ResponseEntity<List<ColumnsResponse>> getColumnSourceInfo(@PathVariable Long groupId) {
		try {
			return ResponseEntity.ok(columnsService.getColumnInfo(groupId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/{groupId}")
	@Operation(summary = "Post Data ", description = "Post data for a specific group identified by groupId")
	public ResponseEntity<List<ColumnsResponse>> savedData(@PathVariable Long groupId,
			@RequestBody List<ColumnsRequest> columnsRequests) {
		try {
			List<ColumnsResponse> savedata = columnsService.saveData(groupId, columnsRequests);
			return ResponseEntity.ok(savedata);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{columnId}")
	@Operation(summary = "Update Column", description = "Updates data for a specific column identified by columnId")
	public ResponseEntity<ColumnsResponse> updateColumn(@PathVariable Long columnId,
			@RequestBody ColumnsRequest columnsRequest) {
		try {
			ColumnsResponse updatedColumn = columnsService.updateColumn(columnId, columnsRequest);
			return ResponseEntity.ok(updatedColumn);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{columnId}")
	@Operation(summary = "Delete Data", description = "Delete data for a specific column identified by columnId")
	public ResponseEntity<ColumnsResponse> deleteData(@PathVariable Long columnId) {
		try {
			columnsService.deleteData(columnId);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
