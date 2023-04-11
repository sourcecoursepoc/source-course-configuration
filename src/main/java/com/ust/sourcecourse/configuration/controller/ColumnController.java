package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.request.ColumnData;
import com.ust.sourcecourse.configuration.response.ColumnInfo;
import com.ust.sourcecourse.configuration.service.GroupColumnService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/column")
public class ColumnController {

	@Autowired
	private GroupColumnService groupColumnService;

	@GetMapping("/group/{groupUid}")
	public ResponseEntity<List<ColumnInfo>> getColumns(@PathVariable Long groupUid) {
		return ResponseEntity.ok(groupColumnService.getColumnsByGroup(groupUid));
	}

	@PutMapping("/{columnUid}")
	public ResponseEntity<ColumnInfo> updateColumn(@PathVariable Long columnUid,
			@Valid @RequestBody ColumnData columnData) {
		return ResponseEntity.ok(groupColumnService.updateColumn(columnUid, columnData));
	}

	@DeleteMapping("/{columnUid}")
	public ResponseEntity<Void> deleteColumn(@PathVariable Long columnUid) {
		groupColumnService.deleteColumn(columnUid);
		return ResponseEntity.noContent().build();
	}
}
