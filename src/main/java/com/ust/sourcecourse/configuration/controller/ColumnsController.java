package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.ust.sourcecourse.configuration.entity.ColumnsEntity;
import com.ust.sourcecourse.configuration.service.ColumnsService;

public class ColumnsController {
	@Autowired
	private ColumnsService columnsService;
	@GetMapping("/getcolumsdetails")
	public List<ColumnsEntity> getAll()
	{
		return columnsService.getAllColumns();
		
	}

}
