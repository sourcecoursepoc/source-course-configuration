package com.ust.sourcecourse.configuration.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.ColumnsEntity;
import com.ust.sourcecourse.configuration.repository.ColumnsRepository;
import com.ust.sourcecourse.configuration.request.ColumnsRequest;

@Service
public class ColumnsService {
//	private static final Object ColumnsEntity = null;
	@Autowired
	private static ColumnsRepository Columnsrepository;

	public List<ColumnsEntity> getAllColumns() {
		return (List<ColumnsEntity>) Columnsrepository.findAll();
	}


	public static  ColumnsEntity PostData(ColumnsEntity entity) {
		// TODO Auto-generated method stub
		
			return Columnsrepository.save(entity);
		
			
		}
	}
	

