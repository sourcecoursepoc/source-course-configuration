package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.ProjectTable;
import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.entity.SourceTable;

import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.repository.ProjectTableRepository;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.request.ProjectTableRequest;
import com.ust.sourcecourse.configuration.response.DBTable;
import com.ust.sourcecourse.configuration.response.DBTableColumn;
import com.ust.sourcecourse.configuration.response.DBTableColumnMetadata;
import com.ust.sourcecourse.configuration.response.DBTableMetadata;
import com.ust.sourcecourse.configuration.response.ProjectInfo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectTableService {

	private static final Logger logger = LoggerFactory.getLogger(ProjectTableService.class);

	@Autowired
	private ProjectTableRepository projectTableRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private SourceTableRepository sourceTableRepository;

	/**
	 * 
	 * @param projTableReq
	 * @return
	 */

	public List<DBTable> createProjectTable(ProjectTableRequest projTableReq) {

		Project project = projectRepository.findByUid(projTableReq.getProjectUid());
		logger.info("Project found: {}", project);

		List<Long> sourceTableId = projTableReq.getSourceTableUids();
		List<SourceTable> sourceTables = sourceTableRepository.findAllById(sourceTableId);
		logger.info("Source tables found: {}", sourceTables);

		List<ProjectTable> projectTables = new ArrayList<>();
		for (SourceTable sourceTable : sourceTables) {
			ProjectTable projectTable = ProjectTable.builder().project(project).sourceTable(sourceTable).build();
			projectTables.add(projectTable);
		}

		List<ProjectTable> savedProjectTables = projectTableRepository.saveAll(projectTables);

		
		List<DBTable> dbTables = sourceTables.stream().map(sourceTable -> getDBTable(sourceTable))
				.toList();

		return dbTables;

	}
	
	private DBTable getDBTable(SourceTable sourceTable) {
		DBTableMetadata metadata = DBTableMetadata.builder().maxDate(sourceTable.getMaxDate())
				.minDate(sourceTable.getMinDate()).momCount(sourceTable.getMomCount())
				.yoyCount(sourceTable.getYoyCount()).rowCount(sourceTable.getRowCount()).size(sourceTable.getSize())
				.build();
		List<DBTableColumn> dbTableColumns = sourceTable.getSourceColumns().stream()
				.map(sourceColumn -> getDBTableColumn(sourceColumn)).toList();
		return DBTable.builder().uid(sourceTable.getUid()).tableName(sourceTable.getName())
				.description(sourceTable.getDescription()).metadata(metadata).tags(sourceTable.getTags())
				.columns(dbTableColumns).build();
	}

	private DBTableColumn getDBTableColumn(SourceColumn sourceColumn) {
		DBTableColumnMetadata metadata = DBTableColumnMetadata.builder().type(sourceColumn.getType())
				.isPrimary(sourceColumn.isPrimary()).isNullable(sourceColumn.isNullable())
				.isUnique(sourceColumn.isUnique()).defaultValue(sourceColumn.getDefaultValue()).build();
		return DBTableColumn.builder().uid(sourceColumn.getUid()).name(sourceColumn.getName())
				.description(sourceColumn.getDescription()).metadata(metadata).tags(sourceColumn.getTags()).build();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ProjectInfo getProjectTables(Long id) {
		
		
		List<ProjectTable> projectTables = projectTableRepository.findByProjectUid(id);
		for(ProjectTable projectTable: projectTables) {
			DBTable dbTable = getDBTable(projectTable.getSourceTable());
		}
		
		
		ProjectTable projTable = projectTableRepository.findById(id).orElse(null);
		if (projTable != null) {

			return ProjectInfo.builder().uid(projTable.getUid()).build();

		} else {
			String errormessage = "project id not found" + id;
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, errormessage);
		}

	}

	public ProjectInfo updateProjectTable(Long id, ProjectTable projectTable) {
		Optional<ProjectTable> existingProjectTable = projectTableRepository.findById(id);
		if (existingProjectTable.isPresent()) {
			ProjectTable updatedProjectTable = projectTableRepository.save(projectTable);
			return ProjectInfo.builder().uid(updatedProjectTable.getUid()).build();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
		}

	}

	public void deleteSourceTable(Long id) {
		Optional<ProjectTable> optionalProjectTable = projectTableRepository.findById(id);
		if (optionalProjectTable.isPresent()) {
			ProjectTable projectTable = optionalProjectTable.get();
			projectTableRepository.delete(projectTable);
		} else {
			throw new EntityNotFoundException("ProjectTable not found with id: " + id);
		}

	}

}
