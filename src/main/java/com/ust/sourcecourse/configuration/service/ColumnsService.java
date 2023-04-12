
package com.ust.sourcecourse.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.repository.SourceColumnRepository;
import com.ust.sourcecourse.configuration.request.ColumnsRequest;
import com.ust.sourcecourse.configuration.response.ColumnsResponse;

@Service
public class ColumnsService {
	@Autowired
	private SourceColumnRepository columnsRepository;

	public List<SourceColumn> getColumnInfo() {
		List<SourceColumn> columns = columnsRepository.findAll();
		return columns;
	}
	
	public ColumnsResponse saveData(ColumnsRequest dbData) {
		SourceColumn sourceColumn = SourceColumn.builder().name(dbData.getName()).description(dbData.getDescription()).build();
		
		   sourceColumn = columnsRepository.save(sourceColumn);
		
		
		  return  ColumnsResponse.builder().uid(sourceColumn.getUid()).name(sourceColumn.getName()).type(sourceColumn.getType()).build();
	    }
	}

