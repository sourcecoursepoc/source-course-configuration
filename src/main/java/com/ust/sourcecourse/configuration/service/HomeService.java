package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.response.HomePageResponse;

@Service
public class HomeService {
	

		@Autowired
		private ProjectRepository projectRepository;

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

//		public Project createProject(Project project) {
//			return projectRepository.save(project);
//		}
//		
//		public Project getProjectById(Long id) {
//			return projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
//		}
//
//		public List<Project> getAllProjects() {
//			return projectRepository.findAll();
//		}
//		
//		public Project updateProject(Long id, Project project) {
//			Project existingProject = projectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
//			existingProject.setName(project.getName());
//			existingProject.setDescription(project.getDescription());
//			existingProject.setModifiedBy(project.getModifiedBy());
//			existingProject.setModifiedTimestamp(LocalDateTime.now());
//			return projectRepository.save(existingProject);
//		}
//		
//		public void deleteProject(Long id) {
//			projectRepository.deleteById(id);
//		}

}
