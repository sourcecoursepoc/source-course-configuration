package com.ust.sourcecourse.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.ConnectionInfo;
import com.ust.sourcecourse.configuration.entity.DataSource;
import com.ust.sourcecourse.configuration.repository.DataSourceRepository;
import com.ust.sourcecourse.configuration.request.DBInfo;
import com.ust.sourcecourse.configuration.response.DBDataSourceInfo;

@Service
public class DBDataSourceService {

	@Autowired
	private DataSourceRepository dataSourceRepository;

//	@Autowired
//	private ConnectionInfoRepository connectionInfoRepository;

	public DBDataSourceInfo saveDB(DBInfo dbInfo) {
		ConnectionInfo connectionInfo = ConnectionInfo.builder().connectionURL(dbInfo.getConnectionURL())
				.username(dbInfo.getUsername()).password(dbInfo.getPassword()).build();
		DataSource dataSource = DataSource.builder().name(dbInfo.getName()).description(dbInfo.getDescription())
				.connectionInfo(connectionInfo).build();
		connectionInfo.setDataSource(dataSource);
		dataSource = dataSourceRepository.save(dataSource);
//		connectionInfo = connectionInfoRepository.save(connectionInfo);
		return DBDataSourceInfo.builder().uid(dataSource.getUid()).name(dataSource.getName())
				.description(dataSource.getDescription()).build();
	}
}
