package com.ust.sourcecourse.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.ConnectionInfo;
import com.ust.sourcecourse.configuration.entity.DataSource;
import com.ust.sourcecourse.configuration.repository.ConnectionInfoRepository;
import com.ust.sourcecourse.configuration.repository.DataSourceRepository;
import com.ust.sourcecourse.configuration.request.DBInfo;
import com.ust.sourcecourse.configuration.response.DBDataSourceInfo;

@Service
public class DBDataSourceService {

	@Autowired
	private DataSourceRepository dataSourceRepository;
	
	@Autowired
	private ConnectionInfoRepository connectionInfoRepository;

	public DBDataSourceInfo saveDB(DBInfo dbInfo) {
		DataSource dataSource = DataSource.builder().name(dbInfo.getName()).description(dbInfo.getDescription())
				.build();
		dataSource = dataSourceRepository.save(dataSource);
		ConnectionInfo connectionInfo = ConnectionInfo.builder().connectionURL(dbInfo.getConnectionURL())
				.username(dbInfo.getUsername()).password(dbInfo.getPassword()).dataSource(dataSource).build();
		connectionInfo = connectionInfoRepository.save(connectionInfo);
		return DBDataSourceInfo.builder().uid(dataSource.getUid()).name(dataSource.getName())
				.description(dataSource.getDescription()).build();
	}
}
