
package com.ust.sourcecourse.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.DataSource;
import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.repository.SourceColumnRepository;
import com.ust.sourcecourse.configuration.response.ColumnsResponse;
import com.ust.sourcecourse.configuration.response.DBDataSourceInfo;

@Service
public class ColumnsService {
//	private static final Object ColumnsEntity = null;
	@Autowired
	private static  SourceColumnRepository columnsRepository;
//	public static  SourceColumn PostColumns(SourceColumn entity) {
//		// TODO Auto-generated method stub
//		
//			return columnsRepository.save(entity);
//		
			
//		}

public List<SourceColumn> getColumnInfo() {
	List<SourceColumn> columns = columnsRepository.findAll();
	return columns;
}
public static  SourceColumn saveData(SourceColumn dbData) {
	SourceColumn savedata = columnsRepository.save(dbData);
	// TODO Auto-generated method stub
	return savedata;
}

}

	
