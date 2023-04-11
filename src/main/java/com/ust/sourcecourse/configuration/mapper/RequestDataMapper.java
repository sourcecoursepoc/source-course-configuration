package com.ust.sourcecourse.configuration.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ust.sourcecourse.configuration.entity.GroupColumn;
import com.ust.sourcecourse.configuration.entity.GroupPipeline;
import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.ProjectGroup;
import com.ust.sourcecourse.configuration.entity.ProjectTable;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.repository.SourceColumnRepository;
import com.ust.sourcecourse.configuration.request.ColumnData;
import com.ust.sourcecourse.configuration.request.PipelineData;

@Component
public class RequestDataMapper {

	@Autowired
	private SourceColumnRepository sourceColumnRepository;

	public ProjectTable getProjectTable(Project project, SourceTable sourceTable) {
		return ProjectTable.builder().project(project).sourceTable(sourceTable).build();
	}

	public GroupPipeline getGroupPipeline(ProjectGroup projectGroup, PipelineData pipelineData) {
		GroupPipeline groupPipeline = projectGroup.getGroupPipeline();
		if (groupPipeline == null) {
			groupPipeline = GroupPipeline.builder().projectGroup(projectGroup).exportType(pipelineData.getExportType())
					.loadType(pipelineData.getLoadType()).recurrence(pipelineData.getRecurrence()).build();
		} else {
			updatePipeline(pipelineData, groupPipeline);
		}
		return groupPipeline;
	}

	public void updatePipeline(PipelineData pipelineData, GroupPipeline groupPipeline) {
		groupPipeline.setExportType(pipelineData.getExportType());
		groupPipeline.setLoadType(pipelineData.getLoadType());
		groupPipeline.setRecurrence(pipelineData.getRecurrence());
	}

	public GroupColumn getGroupColumn(ProjectGroup projectGroup, ColumnData columnData) {
		return GroupColumn.builder().projectGroup(projectGroup).name(columnData.getName()).notes(columnData.getNotes())
				.defaultValue(columnData.getDefaultValue()).prefix(columnData.getPreffix())
				.suffix(columnData.getSuffix())
				.sourceColumn(sourceColumnRepository.findById(columnData.getSourceColumnUid()).orElseThrow()).build();
	}

	public void updateGroupColumn(ColumnData columnData, GroupColumn groupColumn) {
		groupColumn.setName(columnData.getName());
		groupColumn.setNotes(columnData.getNotes());
		groupColumn.setDefaultValue(columnData.getDefaultValue());
		groupColumn.setPrefix(columnData.getPreffix());
		groupColumn.setSuffix(columnData.getSuffix());
		groupColumn.setSourceColumn(sourceColumnRepository.findById(columnData.getSourceColumnUid()).orElseThrow());
	}
}
