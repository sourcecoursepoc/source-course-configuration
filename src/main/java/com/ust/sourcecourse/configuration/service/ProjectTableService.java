package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.ProjectTable;
import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.exception.ResourceNotFoundException;
import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.repository.ProjectTableRepository;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.request.ProjectTableRequest;
import com.ust.sourcecourse.configuration.response.DBTable;
import com.ust.sourcecourse.configuration.response.DBTableColumn;
import com.ust.sourcecourse.configuration.response.DBTableColumnMetadata;
import com.ust.sourcecourse.configuration.response.DBTableMetadata;

@Service
public class ProjectTableService {

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
	@Transactional
	public List<DBTable> createProjectTable(ProjectTableRequest projTableReq) {

		Project project = projectRepository.findByUid(projTableReq.getProjectUid());

		if (project == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
		}
		List<ProjectTable> projectTables = project.getProjectTables();
		if (projTableReq.getSourceTableUids() == null) {
			projTableReq.setSourceTableUids(Collections.emptyList());
		}
		Set<Long> sourceTableIds = new HashSet<>(projTableReq.getSourceTableUids());

		List<ProjectTable> toRemove = new ArrayList<>();
		if (projectTables != null && !projectTables.isEmpty()) {
			for (ProjectTable projectTable : projectTables) {
				Long tableUid = projectTable.getSourceTable().getUid();
				if (sourceTableIds.contains(tableUid)) {
					sourceTableIds.remove(tableUid);
				} else {
					toRemove.add(projectTable);
				}
			}
			;
			if (!toRemove.isEmpty()) {
				projectTables.removeAll(toRemove);
			}
		}

		if (sourceTableIds != null && !sourceTableIds.isEmpty()) {
			List<SourceTable> sourceTables = sourceTableRepository.findAllById(sourceTableIds);
			for (SourceTable sourceTable : sourceTables) {
				ProjectTable projectTable = ProjectTable.builder().project(project).sourceTable(sourceTable).build();
				projectTables.add(projectTable);
			}

			project.setProjectTables(projectTables);
			project = projectRepository.save(project);
		}
		if (!toRemove.isEmpty()) {
			projectTableRepository.deleteAll(toRemove);
		}
		List<DBTable> dbTables = project.getProjectTables().stream()
				.map(projectTable -> getDBTable(projectTable.getSourceTable())).toList();

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

	public List<DBTable> getProjectTables(Long id) {

		Project projObj = projectRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProjectId not found"));
		List<ProjectTable> projectTables = projObj.getProjectTables();

		List<DBTable> tables = projectTables.stream().map(st -> getDBTable(st.getSourceTable())).toList();

		return tables;
	}

	/**
	 * 
	 * @param projectId
	 * @param sourceId
	 */

	public List<Long> deleteProjectTable(ProjectTableRequest projTableReq) throws ResourceNotFoundException {

		List<ProjectTable> projectTableList = projectTableRepository.findByProjectUid(projTableReq.getProjectUid());
		List<Long> deletedUid = new ArrayList<>();
		for (ProjectTable pt : projectTableList) {

			List<Long> sourceTableUids = projTableReq.getSourceTableUids();
			if (sourceTableUids != null && sourceTableUids.contains(pt.getSourceTable().getUid())) {
				Long deleteUid = pt.getUid();
				deletedUid.add(deleteUid);
			}
		}
		if (deletedUid.isEmpty()) {
			throw new ResourceNotFoundException("No project Id found for the given request");
		}
		projectTableRepository.deleteAllById(deletedUid);
		return deletedUid;

	}

}
