package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.ProjectTable;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.mapper.ResponseDataMapper;
import com.ust.sourcecourse.configuration.mapper.RequestDataMapper;
import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.repository.ProjectTableRepository;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.response.ProjectInfo;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectTableService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private SourceTableRepository sourceTableRepository;

	@Autowired
	private ProjectTableRepository projectTableRepository;

	@Autowired
	private ResponseDataMapper responseDataMapper;

	@Autowired
	private RequestDataMapper requestDataMapper;

	@Transactional
	public ProjectInfo addTables(Long projectUid, List<Long> sourceTableUids) {
		log.info("Adding {} for project {}", sourceTableUids, projectUid);
		Project project = projectRepository.findById(projectUid).orElseThrow();
		List<ProjectTable> projectTables = project.getProjectTables();
		if (projectTables == null) {
			projectTables = new ArrayList<>();
		}
		for (ProjectTable projectTable : projectTables) {
			if (sourceTableUids.contains(projectTable.getUid())) {
				sourceTableUids.removeAll(Collections.singleton(projectTable.getUid()));
			}
		}

		log.info("Adding IDs {} for project {}", sourceTableUids, projectUid);

		List<SourceTable> sourceTables = sourceTableRepository.findAllById(sourceTableUids);

		List<ProjectTable> list = sourceTables.stream()
				.map(sourceTable -> requestDataMapper.getProjectTable(project, sourceTable)).toList();
		projectTables.addAll(list);
		project.setProjectTables(projectTables);
		Project project1 = projectRepository.save(project);
		return responseDataMapper.getProjectInfo(project1);
	}

	@Transactional
	public void removeTables(Long projectUid, List<Long> sourceTableUids) {
		log.info("Removing {} from project {}", sourceTableUids, projectUid);
		Optional<Project> prj = projectRepository.findById(projectUid);
		Project project = prj.orElseThrow();
		List<ProjectTable> projectTables = project.getProjectTables();
		if (projectTables == null) {
			projectTables = new ArrayList<>();
		}
		List<ProjectTable> tablesToRemove = new ArrayList<>();
		for (ProjectTable projectTable : projectTables) {
			if (sourceTableUids.contains(projectTable.getSourceTable().getUid())) {
				tablesToRemove.add(projectTable);
			}
		}
		projectTableRepository.deleteAllInBatch(tablesToRemove);
	}
}
