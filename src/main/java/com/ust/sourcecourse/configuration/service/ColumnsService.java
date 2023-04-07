package com.ust.sourcecourse.configuration.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.ColumnsEntity;
import com.ust.sourcecourse.configuration.repository.ColumnsRepository;

@Service
public class ColumnsService {
//	private static final Object ColumnsEntity = null;
	@Autowired
	ColumnsRepository Columnsrepository;

	public List<ColumnsEntity> getAllColumns() {
		return (List<ColumnsEntity>) Columnsrepository.findAll();
	}
}
