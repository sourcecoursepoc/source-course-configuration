package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.response.HomePageResponse;

@Service
public class HomeService {

	@Autowired
	private ProjectRepository projectRepository;

	public List<HomePageResponse> getHomePageDetails() {
		List<Project> projects = projectRepository.findAll();
		if (projects.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No projects found");
	    }
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

}
