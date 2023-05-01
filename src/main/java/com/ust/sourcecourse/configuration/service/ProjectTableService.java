package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.ProjectTable;
import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.exception.CustomException;
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

	public List<DBTable> createProjectTable(ProjectTableRequest projTableReq) {

		Project project = projectRepository.findByUid(projTableReq.getProjectUid());

		if (project == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
		}

		List<Long> sourceTableId = projTableReq.getSourceTableUids();
		List<ProjectTable> projectTables2 = project.getProjectTables();
		for (ProjectTable projTable : projectTables2) {
			if (sourceTableId != null && sourceTableId.contains(projTable.getSourceTable().getUid())) {
				sourceTableId.remove(projTable.getSourceTable().getUid());
			}

		}
		List<SourceTable> sourceTables = sourceTableRepository.findAllById(sourceTableId);

		List<ProjectTable> projectTables = project.getProjectTables();
		for (SourceTable sourceTable : sourceTables) {
			ProjectTable projectTable = ProjectTable.builder().project(project).sourceTable(sourceTable).build();
			if (projectTables.contains(projectTable)) {

				throw new ResponseStatusException(HttpStatus.CONFLICT, "Project table already exists");
			}
			projectTables.add(projectTable);
		}
		project.setProjectTables(projectTables);
		project = projectRepository.save(project);

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

	public List<Long> deleteProjectTable(ProjectTableRequest projTableReq) throws CustomException{

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
            throw new CustomException("No project Id found for the given request");
        }
		projectTableRepository.deleteAllById(deletedUid);
		return deletedUid;

	}

}
