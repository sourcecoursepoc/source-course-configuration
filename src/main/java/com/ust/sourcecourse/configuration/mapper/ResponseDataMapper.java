package com.ust.sourcecourse.configuration.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ust.sourcecourse.configuration.entity.GroupColumn;
import com.ust.sourcecourse.configuration.entity.GroupPipeline;
import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.ProjectGroup;
import com.ust.sourcecourse.configuration.entity.ProjectTable;
import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.response.AttributesInfo;
import com.ust.sourcecourse.configuration.response.ColumnInfo;
import com.ust.sourcecourse.configuration.response.DBTable;
import com.ust.sourcecourse.configuration.response.DBTableColumn;
import com.ust.sourcecourse.configuration.response.DBTableColumnMetadata;
import com.ust.sourcecourse.configuration.response.DBTableMetadata;
import com.ust.sourcecourse.configuration.response.GroupInfo;
import com.ust.sourcecourse.configuration.response.PipelineInfo;
import com.ust.sourcecourse.configuration.response.ProjectInfo;

@Component
public class ResponseDataMapper {

	public ProjectInfo getProjectInfo(Project project) {
		ProjectInfo projectInfo = ProjectInfo.builder().uid(project.getUid()).name(project.getName())
				.description(project.getDescription()).build();
		List<ProjectTable> projectTables = project.getProjectTables();
		if (projectTables != null) {
			List<DBTable> dbTables = projectTables.stream()
					.map(projectTable -> getDBTable(projectTable.getSourceTable())).toList();
			projectInfo.setTables(dbTables);
		}

		List<ProjectGroup> projectGroups = project.getGroups();
		if (projectGroups != null) {
			List<GroupInfo> groups = projectGroups.stream().map(projectGroup -> getGroupInfo(projectGroup)).toList();
			projectInfo.setGroups(groups);
		}

		return projectInfo;
	}

	public List<DBTable> getDBTables(List<SourceTable> sourceTables) {
		return sourceTables.stream().map(sourceTable -> getDBTable(sourceTable)).toList();
	}

	public DBTable getDBTable(SourceTable sourceTable) {
		DBTableMetadata metadata = DBTableMetadata.builder().maxDate(sourceTable.getMaxDate())
				.minDate(sourceTable.getMinDate()).momCount(sourceTable.getMomCount())
				.yoyCount(sourceTable.getYoyCount()).rowCount(sourceTable.getRowCount()).size(sourceTable.getSize())
				.build();
		List<SourceColumn> sourceColumns = sourceTable.getSourceColumns();
		DBTable dbTable = DBTable.builder().uid(sourceTable.getUid()).tableName(sourceTable.getName())
				.description(sourceTable.getDescription()).metadata(metadata).tags(sourceTable.getTags()).build();
		if (sourceColumns != null) {
			List<DBTableColumn> dbTableColumns = sourceColumns.stream()
					.map(sourceColumn -> getDBTableColumn(sourceColumn)).toList();
			dbTable.setColumns(dbTableColumns);
		}
		return dbTable;
	}

	public DBTableColumn getDBTableColumn(SourceColumn sourceColumn) {
		DBTableColumnMetadata metadata = DBTableColumnMetadata.builder().type(sourceColumn.getType())
				.isPrimary(sourceColumn.isPrimary()).isNullable(sourceColumn.isNullable())
				.isUnique(sourceColumn.isUnique()).defaultValue(sourceColumn.getDefaultValue()).build();
		return DBTableColumn.builder().uid(sourceColumn.getUid()).name(sourceColumn.getName())
				.description(sourceColumn.getDescription()).metadata(metadata).tags(sourceColumn.getTags()).build();
	}

	public GroupInfo getGroupInfo(ProjectGroup projectGroup) {
		GroupInfo groupInfo = GroupInfo.builder().uid(projectGroup.getUid()).name(projectGroup.getName())
				.description(projectGroup.getDescription()).tags(projectGroup.getTags()).build();
		List<GroupColumn> groupColumns = projectGroup.getGroupColumns();
		if (groupColumns != null) {
			List<ColumnInfo> columnInfos = getColumnInfos(groupColumns);
			groupInfo.setColumnInfos(columnInfos);
		}

		GroupPipeline groupPipeline = projectGroup.getGroupPipeline();
		if (groupPipeline != null) {
			PipelineInfo pipelineInfo = getPipelineInfo(groupPipeline);
			groupInfo.setPipeline(pipelineInfo);
		}
		return groupInfo;
	}

	public List<ColumnInfo> getColumnInfos(List<GroupColumn> groupColumns) {
		List<ColumnInfo> columnInfos = groupColumns.stream().map(groupColumn -> getGroupColumnInfo(groupColumn))
				.toList();
		return columnInfos;
	}

	public PipelineInfo getPipelineInfo(GroupPipeline groupPipeline) {
		PipelineInfo pipelineInfo = PipelineInfo.builder().uid(groupPipeline.getUid())
				.exportType(groupPipeline.getExportType()).loadType(groupPipeline.getLoadType())
				.recurrence(groupPipeline.getRecurrence()).build();
		return pipelineInfo;
	}

	public ColumnInfo getGroupColumnInfo(GroupColumn groupColumn) {
		AttributesInfo attributesInfo = AttributesInfo.builder().name(groupColumn.getName())
				.notes(groupColumn.getNotes()).defaultValue(groupColumn.getDefaultValue())
				.preffix(groupColumn.getPrefix()).suffix(groupColumn.getSuffix()).build();
		return ColumnInfo.builder().uid(groupColumn.getUid()).attributesInfo(attributesInfo)
				.sourceColumn(getDBTableColumn(groupColumn.getSourceColumn())).build();
	}

}
