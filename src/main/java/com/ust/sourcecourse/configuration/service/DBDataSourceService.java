
package com.ust.sourcecourse.configuration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.ConnectionInfo;
import com.ust.sourcecourse.configuration.entity.DataSource;
import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.repository.DataSourceRepository;
import com.ust.sourcecourse.configuration.repository.SourceColumnRepository;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.request.DBData;
import com.ust.sourcecourse.configuration.request.HomeRequest;
import com.ust.sourcecourse.configuration.response.DBDataSourceInfo;
import com.ust.sourcecourse.configuration.response.DBMetadata;
import com.ust.sourcecourse.configuration.response.DBTable;
import com.ust.sourcecourse.configuration.response.DBTableColumn;
import com.ust.sourcecourse.configuration.response.DBTableColumnMetadata;
import com.ust.sourcecourse.configuration.response.DBTableMetadata;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class DBDataSourceService {

	@Autowired
	private DataSourceRepository dataSourceRepository;
	@Autowired
	private SourceColumnRepository sourceColumnRepository;
	@Autowired
	private SourceTableRepository sourceTableRepository;

	@Transactional
	public DBDataSourceInfo saveDB(DBData dbData) {
		ConnectionInfo connectionInfo = ConnectionInfo.builder().connectionURL(dbData.getConnectionURL())
				.username(dbData.getUsername()).password(dbData.getPassword()).build();
		DataSource dataSource = DataSource.builder().name(dbData.getName()).description(dbData.getDescription())
				.connectionInfo(connectionInfo).build();
		connectionInfo.setDataSource(dataSource);
		dataSource = dataSourceRepository.save(dataSource);
		return getDBDataSource(dataSource);
	}

	public void deleteDB(Long uid) {
		dataSourceRepository.deleteById(uid);
	}

	public List<DBDataSourceInfo> getDBInfo() {
		List<DataSource> dataSources = dataSourceRepository.findAll();
		return dataSources.stream().map(dataSource -> getDBDataSourceInfo(dataSource)).toList();
	}

	private DBDataSourceInfo getDBDataSource(DataSource dataSource) {
		return DBDataSourceInfo.builder().uid(dataSource.getUid()).dbName(dataSource.getName())
				.description(dataSource.getDescription()).build();
	}

	private DBDataSourceInfo getDBDataSourceInfo(DataSource dataSource) {
		DBDataSourceInfo dataSourceInfo = getDBDataSource(dataSource);
		DBMetadata metadata = DBMetadata.builder().region(dataSource.getRegion()).size(dataSource.getSize())
				.status(dataSource.getStatus()).totalTables(dataSource.getTotalTables()).build();
		dataSourceInfo.setMetadata(metadata);
		List<DBTable> dbTables = dataSource.getSourceTables().stream().map(sourceTable -> getDBTable(sourceTable))
				.toList();
		dataSourceInfo.setTables(dbTables);
		return dataSourceInfo;
	}

	private DBTable getDBTable(SourceTable sourceTable) {
		DBTableMetadata metadata = DBTableMetadata.builder().maxDate(sourceTable.getMaxDate())
				.minDate(sourceTable.getMinDate()).momCount(sourceTable.getMomCount())
				.yoyCount(sourceTable.getYoyCount()).rowCount(sourceTable.getRowCount()).size(sourceTable.getSize())
				.build();
		List<DBTableColumn> dbTableColumns = sourceTable.getSourceColumns().stream()
				.map(sourceColumn -> getDBTableColumn1(sourceColumn)).toList();
		return DBTable.builder().uid(sourceTable.getUid()).tableName(sourceTable.getName())
				.description(sourceTable.getDescription()).metadata(metadata).tags(sourceTable.getTags())
				.columns(dbTableColumns).build();
	}

	private DBTableColumn getDBTableColumn1(SourceColumn sourceColumn) {
		DBTableColumnMetadata metadata = DBTableColumnMetadata.builder().type(sourceColumn.getType())
				.isPrimary(sourceColumn.isPrimary()).isNullable(sourceColumn.isNullable())
				.isUnique(sourceColumn.isUnique()).defaultValue(sourceColumn.getDefaultValue()).build();
		return DBTableColumn.builder().uid(sourceColumn.getUid()).name(sourceColumn.getName())
				.description(sourceColumn.getDescription()).metadata(metadata).tags(sourceColumn.getTags()).build();
	}

	public List<String> getDescriptionAndTags(Long uid) {
		SourceTable homePage = sourceTableRepository.findById(uid).orElseThrow();

		return homePage.getTags();
	}

	public List<String> getColumnTags(Long uid) {
		Optional<SourceColumn> column = sourceColumnRepository.findById(uid);
		if (column.isPresent()) {
			return column.get().getTags();
		} else {
			throw new EntityNotFoundException("SourceColumn with uid " + uid + " not found");
		}
	}

	public List<String> getTableTags(Long uid) {
		Optional<SourceTable> table = sourceTableRepository.findById(uid);
		if (table.isPresent()) {
			return table.get().getTags();
		} else {
			throw new EntityNotFoundException("SourceTable with uid " + uid + " not found");
		}
	}

	public SourceColumn updateColumnDescriptionAndTags(Long uid, HomeRequest request) {
		Optional<SourceColumn> column = sourceColumnRepository.findById(uid);
		if (column.isPresent()) {
			column.get().setTags(request.getTags());
			sourceColumnRepository.save(column.get());
			return column.get();
		} else {
			throw new EntityNotFoundException("SourceColumn with uid " + uid + " not found");
		}
	}

	public SourceTable updateTableDescriptionAndTags(Long uid, HomeRequest request) {
		Optional<SourceTable> table = sourceTableRepository.findById(uid);
		if (table.isPresent()) {
			table.get().setTags(request.getTags());
			sourceTableRepository.save(table.get());
			return table.get();
		} else {
			throw new EntityNotFoundException("SourceTable with uid " + uid + " not found");
		}
	}

	public void deleteColumnTags(Long uid) {
		Optional<SourceColumn> column = sourceColumnRepository.findById(uid);
		if (column.isPresent()) {
			List<String> Tags=column.get().getTags();
			sourceColumnRepository.delete((SourceColumn) Tags);
//			sourceColumnRepository.save(column.get());
		} else {
			throw new EntityNotFoundException("SourceColumn with uid " + uid + " not found");
		}
	}

	public void deleteTableTags(Long uid) {
		Optional<SourceTable> table = sourceTableRepository.findById(uid);
		if (table.isPresent()) {
			
			List<String> tags=table.get().getTags();
			
			sourceTableRepository.delete((SourceTable) tags);
		} else {
			throw new EntityNotFoundException("SourceTable with uid " + uid + " not found");
		}
	}
}
