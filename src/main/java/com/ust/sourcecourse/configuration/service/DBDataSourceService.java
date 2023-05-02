
package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ust.sourcecourse.configuration.entity.ConnectionInfo;
import com.ust.sourcecourse.configuration.entity.DataSource;
import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.exception.ResourceNotFoundException;
import com.ust.sourcecourse.configuration.repository.DataSourceRepository;
import com.ust.sourcecourse.configuration.repository.SourceColumnRepository;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.request.DBData;
import com.ust.sourcecourse.configuration.response.DBDataSourceInfo;
import com.ust.sourcecourse.configuration.response.DBMetadata;
import com.ust.sourcecourse.configuration.response.DBTable;
import com.ust.sourcecourse.configuration.response.DBTableColumn;
import com.ust.sourcecourse.configuration.response.DBTableColumnMetadata;
import com.ust.sourcecourse.configuration.response.DBTableMetadata;

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

	public List<String> getTagsByColumn(Long uid) {
		SourceColumn sourceColumn = sourceColumnRepository.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("SourceColumn for given table not found", "id", uid));
		return sourceColumn.getTags();
	}

	public List<String> getTagsByTable(Long uid) {
		SourceTable sourceTable = sourceTableRepository.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("SourceTable with uid " + uid + " not found"));
		return sourceTable.getTags();
	}

	public ResponseEntity<String> removeTagFromSourceColumn(Long uid, String tag) {
		SourceColumn updatedSourceColumn = sourceColumnRepository.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("SourceColumn", "id", uid));
		List<String> tags = updatedSourceColumn.getTags();
		if (tags.remove(tag)) {
			updatedSourceColumn.setTags(tags);
			sourceColumnRepository.save(updatedSourceColumn);
			return ResponseEntity.ok("Tag '" + tag + "' deleted successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<String> removeTagFromSourceTable(Long uid, String tag) {
		SourceTable updatedSourcetable = sourceTableRepository.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("SourceTable", "id", uid));
		List<String> tags = updatedSourcetable.getTags();
		if (tags.remove(tag)) {
			updatedSourcetable.setTags(tags);
			sourceTableRepository.save(updatedSourcetable);
			return ResponseEntity.ok("Tag '" + tag + "' deleted successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public List<DBTable> searchTablesByTag(String tag) {
		List<SourceTable> sourcetables = sourceTableRepository.findAll();
		if (sourcetables.isEmpty()) {
			 throw new ResponseStatusException(HttpStatus.NO_CONTENT,"No tables found in the database.");
		}

		List<DBTable> result = sourcetables.stream()
				.filter(sourceTable -> sourceTable.getTags() != null && sourceTable.getTags().contains(tag))
				.map(sourceTable -> getDBTable(sourceTable)).collect(Collectors.toList());
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,"No tables found with tag '" + tag + "'.");
		}
		return result;
	}

	public List<DBTableColumn> searchcolumnByTag(String tag) {
		List<SourceColumn> sourcecolumns = sourceColumnRepository.findAll();
		if (sourcecolumns.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,"No tables found in the database.");
		}
			return sourcecolumns.stream()
				.filter(sourcecolumn -> sourcecolumn.getTags() != null && sourcecolumn.getTags().contains(tag))
				.map(sourcecolumn -> getDBTableColumn1(sourcecolumn)).collect(Collectors.toList());
	}

	public List<String> addTagSourceTable(Long uid, List<String> tags, String description) {
		SourceTable sourceTable = sourceTableRepository.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("sourcetable", "id", uid));
		if (tags == null) {
	        throw new IllegalArgumentException("tags must not be null");
	    }

		List<String> tagList = sourceTable.getTags();
		if (tagList == null) {
			tagList = new ArrayList<>();
		}
		tagList.addAll(tags);
		Set<String> tagSet = new LinkedHashSet<>(tagList);
		sourceTable.setTags(new ArrayList<>(tagSet));

		sourceTable.setDescription(description); // Set the description

		sourceTableRepository.save(sourceTable);

		return sourceTable.getTags();
	}

	public List<String> addTagSourceColumn(Long uid, List<String> tags, String description) {
		SourceColumn sourceColumn = sourceColumnRepository.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("sourceColumn ", "id", uid));
		if (tags == null) {
	        throw new IllegalArgumentException("tags must not be null");
	    }
		List<String> tagList = sourceColumn.getTags();
		if (tagList == null) {
			tagList = new ArrayList<>();
		}
		tagList.addAll(tags);
		Set<String> tagSet = new LinkedHashSet<>(tagList);
		sourceColumn.setTags(new ArrayList<>(tagSet));

		sourceColumn.setDescription(description); // Set the description

		sourceColumnRepository.save(sourceColumn);

		return sourceColumn.getTags();
	}
}
