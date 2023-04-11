package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.ProjectTable;
import com.ust.sourcecourse.configuration.entity.SourceTable;

import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.repository.ProjectTableRepository;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.request.ProjectTableRequest;

import com.ust.sourcecourse.configuration.response.ProjectTableResponse;

import jakarta.persistence.EntityNotFoundException;

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

	public List<ProjectTableResponse> createProjectTable(ProjectTableRequest projTableReq) {

		Project project = projectRepository.findByUid(projTableReq.getProjectUid());

		List<Long> sourceTableId = projTableReq.getSourceTableUids();
		List<SourceTable> sourceTables = sourceTableRepository.findAllById(sourceTableId);

		List<ProjectTable> projectTables = new ArrayList<>();
		for (SourceTable sourceTable : sourceTables) {
			ProjectTable projectTable = ProjectTable.builder().project(project).sourceTable(sourceTable).build();
			projectTables.add(projectTable);
		}

		projectTables = projectTableRepository.saveAll(projectTables);

		List<ProjectTableResponse> projectTableResponses = new ArrayList<>();
		for (ProjectTable projectTable : projectTables) {

			ProjectTableResponse projectTableResponse = ProjectTableResponse.builder().uid(projectTable.getUid())
					.createdBy(projectTable.getCreatedBy()).createdTimestamp(projectTable.getCreatedTimestamp())
					.modifiedBy(projectTable.getModifiedBy()).modifiedTimestamp(projectTable.getModifiedTimestamp())
					.build();
			projectTableResponses.add(projectTableResponse);
		}

		return projectTableResponses;
	}

	public Optional<ProjectTable> getProjectTable(Long id) {
		return projectTableRepository.findById(id);
	}

	public Optional<ProjectTable> updateProjectTable(Long id, ProjectTable projectTable) {
		Optional<ProjectTable> existingProjectTable = projectTableRepository.findById(id);
		if (existingProjectTable.isPresent()) {
			ProjectTable updatedProjectTable = projectTableRepository.save(projectTable);
			return Optional.of(updatedProjectTable);
		} else {
			return Optional.empty();
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
