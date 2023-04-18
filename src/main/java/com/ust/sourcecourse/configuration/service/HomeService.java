package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.repository.SourceColumnRepository;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.request.HomeRequest;
import com.ust.sourcecourse.configuration.response.DBTableColumn;
import com.ust.sourcecourse.configuration.response.DBTableColumnMetadata;
import com.ust.sourcecourse.configuration.response.HomePageResponse;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HomeService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private SourceColumnRepository sourceColumnRepository;
	@Autowired
	private SourceTableRepository sourceTableRepository;

	public List<HomePageResponse> getHomePageDetails() {
		List<Project> projects = projectRepository.findAll();
		List<HomePageResponse> homePageResponses = new ArrayList<>();
		for (Project project : projects) {
			HomePageResponse response = HomePageResponse.builder().projectName(project.getName())
					.description(project.getDescription()).build();
			response.setTables(project.getProjectTables().size());
			response.setGroups(project.getGroups().size());
			homePageResponses.add(response);
		}
		return homePageResponses;
	}

	public List<String> getDescriptionAndTags(Long uid) {
		SourceTable homePage = sourceTableRepository.findById(uid).orElseThrow();

		return homePage.getTags();
	}

	public DBTableColumn UpdateDescriptionandTags(Long uid, HomeRequest request) {
		SourceColumn homePage = sourceColumnRepository.findById(uid).orElseThrow();
		homePage.setDescription(request.getDescription());
		homePage.setTags(request.getTags());
		SourceColumn SchemaPage = sourceColumnRepository.save(homePage);
		return getDBTableColumn(SchemaPage);
	}

	private DBTableColumn getDBTableColumn(SourceColumn sourceColumn) {
		DBTableColumnMetadata metadata = DBTableColumnMetadata.builder().type(sourceColumn.getType())
				.isPrimary(sourceColumn.isPrimary()).isNullable(sourceColumn.isNullable())
				.isUnique(sourceColumn.isUnique()).defaultValue(sourceColumn.getDefaultValue()).build();
		return DBTableColumn.builder().uid(sourceColumn.getUid()).name(sourceColumn.getName())
				.description(sourceColumn.getDescription()).metadata(metadata).tags(sourceColumn.getTags()).build();
	}

	public DBTableColumn UpdateDescriptionAndRemoveAllTags(Long uid, HomeRequest request) {
		SourceColumn sourceColumn = sourceColumnRepository.findById(uid).orElseThrow();
		sourceColumn.setDescription(request.getDescription());
		sourceColumn.getTags().clear(); // Remove all tags
		SourceColumn updatedSourceColumn = sourceColumnRepository.save(sourceColumn);
		return getDBTableColumn(updatedSourceColumn);
	}

	public void deleteTableTags(Long uid) {
		SourceTable sourceTable = sourceTableRepository.findById(uid)
				.orElseThrow(() -> new EntityNotFoundException("Table not found"));
		sourceTable.getTags().clear();
		sourceTableRepository.save(sourceTable);
	}

	public void deleteColumnTags(Long uid) {
		SourceColumn sourceColumn = sourceColumnRepository.findById(uid)
				.orElseThrow(() -> new EntityNotFoundException("Column not found"));
		sourceColumn.getTags().clear();
		sourceColumnRepository.save(sourceColumn);
	}

}
