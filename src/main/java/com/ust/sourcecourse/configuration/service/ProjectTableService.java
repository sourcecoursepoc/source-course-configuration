package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.ProjectTable;
import com.ust.sourcecourse.configuration.entity.SourceTable;

import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.repository.ProjectTableRepository;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.request.ProjectTableRequest;
import com.ust.sourcecourse.configuration.response.DBTable;
import com.ust.sourcecourse.configuration.response.ProjectInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

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

	public List<ProjectInfo> createProjectTable(ProjectTableRequest projTableReq) {

		Project project = projectRepository.findByUid(projTableReq.getProjectUid());

		List<Long> sourceTableId = projTableReq.getSourceTableUids();
		List<SourceTable> sourceTables = sourceTableRepository.findAllById(sourceTableId);

		List<ProjectTable> projectTables = new ArrayList<>();
		for (SourceTable sourceTable : sourceTables) {
			ProjectTable projectTable = ProjectTable.builder().project(project).sourceTable(sourceTable).build();
			projectTables.add(projectTable);
		}

		List<ProjectTable> savedProjectTables = projectTableRepository.saveAll(projectTables);
		
		/* List<ProjectInfo> projectTableResponses = new ArrayList<>(); 
		 for(ProjectTable projectTable : projectTables) {
		  */
		
		  return savedProjectTables.stream().map(projectTable -> ProjectInfo.builder().uid(projectTable.getUid())
		  .name(project.getName()).description(project.getDescription()).build()).
		  collect(Collectors.toList());
		
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ProjectInfo getProjectTable(Long id) {
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

	/*
	 * public List<ProjectTableResponse> createProjectTable(ProjectTableRequest
	 * projTableReq) {
	 * 
	 * Project project = projectRepository.findByUid(projTableReq.getProjectUid());
	 * 
	 * List<Long> sourceTableId = projTableReq.getSourceTableUids();
	 * List<SourceTable> sourceTables =
	 * sourceTableRepository.findAllById(sourceTableId);
	 * 
	 * List<ProjectTable> projectTables = new ArrayList<>(); for (SourceTable
	 * sourceTable : sourceTables) { ProjectTable projectTable =
	 * ProjectTable.builder().project(project).sourceTable(sourceTable).build();
	 * projectTables.add(projectTable); }
	 * 
	 * projectTables = projectTableRepository.saveAll(projectTables);
	 * 
	 * List<ProjectTableResponse> projectTableResponses = new ArrayList<>(); for
	 * (ProjectTable projectTable : projectTables) {
	 * 
	 * ProjectTableResponse projectTableResponse =
	 * ProjectTableResponse.builder().uid(projectTable.getUid())
	 * .createdBy(projectTable.getCreatedBy()).createdTimestamp(projectTable.
	 * getCreatedTimestamp())
	 * .modifiedBy(projectTable.getModifiedBy()).modifiedTimestamp(projectTable.
	 * getModifiedTimestamp()) .build();
	 * projectTableResponses.add(projectTableResponse); }
	 * 
	 * return projectTableResponses; }
	 */

}
