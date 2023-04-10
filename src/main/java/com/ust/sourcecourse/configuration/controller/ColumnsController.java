package com.ust.sourcecourse.configuration.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.ust.sourcecourse.configuration.entity.ColumnsEntity;
import com.ust.sourcecourse.configuration.entity.SourcePocEntity;
import com.ust.sourcecourse.configuration.repository.SourcePocRepository;
import com.ust.sourcecourse.configuration.service.ColumnsService;

public class ColumnsController {
	@Autowired
	private ColumnsService columnsService;
	@GetMapping("/getcolumsdetails")
	public List<ColumnsEntity> getAll()
	{
		return columnsService.getAllColumns();
		
	}
	@PostMapping("/PostValue")
	public ColumnsEntity PostValues(@RequestBody ColumnsEntity entity ){
		
		return ColumnsService.PostData(entity);
		
		
		
	}
	
}
