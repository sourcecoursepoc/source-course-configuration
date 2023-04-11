package com.ust.sourcecourse.configuration.service;

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
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.request.ProjectData;
import com.ust.sourcecourse.configuration.response.ProjectInfo;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private SourceTableRepository sourceTableRepository;

	@Autowired
	private ResponseDataMapper responseDataMapper;

	@Autowired
	private RequestDataMapper requestDataMapper;

	public ProjectInfo createProject(ProjectData projectData) {
		Project project = Project.builder().name(projectData.getName()).description(projectData.getDescription())
				.build();

		List<Long> tables = projectData.getTables();
		if (tables != null && !tables.isEmpty()) {
			List<SourceTable> sourceTables = sourceTableRepository.findAllById(tables);
			List<ProjectTable> projectTables = sourceTables.stream()
					.map(sourceTable -> requestDataMapper.getProjectTable(project, sourceTable)).toList();
			project.setProjectTables(projectTables);
		}

		return saveProject(project);
	}

	public ProjectInfo updateProject(Long projectUid, ProjectData projectData) {
		Optional<Project> prj = projectRepository.findById(projectUid);
		Project project = prj.orElseThrow();
		project.setName(projectData.getName());
		project.setDescription(projectData.getDescription());
		return saveProject(project);
	}

	public ProjectInfo getProject(Long projectUid) {
		Optional<Project> prj = projectRepository.findById(projectUid);
		Project project = prj.orElseThrow();
		return responseDataMapper.getProjectInfo(project);
	}

	public List<ProjectInfo> getAllProjects() {
		List<Project> projects = projectRepository.findAll();
		return projects.stream().map(project -> responseDataMapper.getProjectInfo(project)).toList();
	}

	public void deleteProject(Long uid) {
		projectRepository.deleteById(uid);
	}

	private ProjectInfo saveProject(Project project) {
		project = projectRepository.save(project);
		return responseDataMapper.getProjectInfo(project);
	}

}
