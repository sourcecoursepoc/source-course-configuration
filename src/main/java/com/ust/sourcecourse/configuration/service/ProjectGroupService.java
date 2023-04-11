package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.GroupColumn;
import com.ust.sourcecourse.configuration.entity.GroupPipeline;
import com.ust.sourcecourse.configuration.entity.Project;
import com.ust.sourcecourse.configuration.entity.ProjectGroup;
import com.ust.sourcecourse.configuration.mapper.RequestDataMapper;
import com.ust.sourcecourse.configuration.mapper.ResponseDataMapper;
import com.ust.sourcecourse.configuration.repository.ProjectGroupRepository;
import com.ust.sourcecourse.configuration.repository.ProjectRepository;
import com.ust.sourcecourse.configuration.request.ColumnData;
import com.ust.sourcecourse.configuration.request.GroupData;
import com.ust.sourcecourse.configuration.request.PipelineData;
import com.ust.sourcecourse.configuration.response.GroupInfo;

@Service
public class ProjectGroupService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectGroupRepository projectGroupRepository;

	@Autowired
	private ResponseDataMapper responseDataMapper;

	@Autowired
	private RequestDataMapper requestDataMapper;

	public List<GroupInfo> getGroups(Long projectUid) {
		List<ProjectGroup> projectGroups = projectGroupRepository.findByProjectUid(projectUid);
		return projectGroups.stream().map(projectGroup -> responseDataMapper.getGroupInfo(projectGroup)).toList();
	}

	public GroupInfo getGroup(Long groupUid) {
		ProjectGroup projectGroup = projectGroupRepository.findById(groupUid).orElseThrow();
		return responseDataMapper.getGroupInfo(projectGroup);
	}

	public GroupInfo addGroup(Long projectUid, GroupData groupData) {
		Optional<Project> prj = projectRepository.findById(projectUid);
		Project project = prj.orElseThrow();
		List<String> tags = groupData.getTags();
		ProjectGroup projectGroup = ProjectGroup.builder().name(groupData.getName())
				.description(groupData.getDescription()).project(project).tags(tags).build();

		List<ColumnData> columnDatas = groupData.getColumns();
		if (columnDatas != null && !columnDatas.isEmpty()) {
			List<GroupColumn> groupColumns = columnDatas.stream()
					.map(columnData -> requestDataMapper.getGroupColumn(projectGroup, columnData)).toList();
			projectGroup.setGroupColumns(groupColumns);
		}

		PipelineData pipelineData = groupData.getPipeline();
		if (pipelineData != null) {
			GroupPipeline groupPipeline = requestDataMapper.getGroupPipeline(projectGroup, pipelineData);
			projectGroup.setGroupPipeline(groupPipeline);
		}

		ProjectGroup group = projectGroupRepository.save(projectGroup);
		return responseDataMapper.getGroupInfo(group);
	}

	public GroupInfo updateGroup(Long groupUid, GroupData groupData) {
		Optional<ProjectGroup> prjGroup = projectGroupRepository.findById(groupUid);
		ProjectGroup projectGroup = prjGroup.orElseThrow();
		projectGroup.setName(groupData.getName());
		projectGroup.setDescription(groupData.getDescription());
		List<String> tags = groupData.getTags();
		if (tags == null || !tags.isEmpty()) {
			projectGroup.setTags(groupData.getTags());
		}
		projectGroup = projectGroupRepository.save(projectGroup);
		return responseDataMapper.getGroupInfo(projectGroup);
	}

	public List<String> addTags(Long groupUid, List<String> tags) {
		Optional<ProjectGroup> prjGroup = projectGroupRepository.findById(groupUid);
		ProjectGroup projectGroup = prjGroup.orElseThrow();
		List<String> groupTags = projectGroup.getTags();
		if (groupTags == null) {
			groupTags = new ArrayList<>();
		}
		Set<String> tagSet = new LinkedHashSet<>(groupTags);
		tagSet.addAll(tags);
		projectGroup.setTags(new ArrayList<String>(tagSet));
		projectGroupRepository.save(projectGroup);
		return getTags(groupUid);
	}

	public void removeTags(Long groupUid, List<String> tags) {
		Optional<ProjectGroup> prjGroup = projectGroupRepository.findById(groupUid);
		ProjectGroup projectGroup = prjGroup.orElseThrow();
		List<String> groupTags = projectGroup.getTags();
		groupTags.removeAll(tags);
		projectGroupRepository.save(projectGroup);
	}

	public List<String> getTags(Long groupUid) {
		ProjectGroup projectGroup = projectGroupRepository.findById(groupUid).orElseThrow();
		return projectGroup.getTags();
	}

}
