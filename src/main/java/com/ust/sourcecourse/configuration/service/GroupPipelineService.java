package com.ust.sourcecourse.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.ust.sourcecourse.configuration.entity.GroupPipeline;
import com.ust.sourcecourse.configuration.entity.ProjectGroup;
import com.ust.sourcecourse.configuration.exception.ResourceNotFoundException;
import com.ust.sourcecourse.configuration.repository.GroupPipelineRepository;
import com.ust.sourcecourse.configuration.repository.ProjectGroupRepository;
import com.ust.sourcecourse.configuration.request.GroupPipelineRequest;
import com.ust.sourcecourse.configuration.response.GroupPipelineResponse;

import jakarta.transaction.Transactional;

@Service
public class GroupPipelineService {

	@Autowired

	private GroupPipelineRepository groupPipelineRepository;

	@Autowired
	private ProjectGroupRepository projectGroupRepository;

	/**
	 * 
	 * @param uid
	 * @param groupPipelineRequest
	 * @return
	 * @throws HttpRequestMethodNotSupportedException
	 */
	public GroupPipelineResponse createGroupPipeline(Long uid, GroupPipelineRequest groupPipelineRequest)
			throws HttpRequestMethodNotSupportedException {
		ProjectGroup projectGroup = projectGroupRepository.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("Project group not found with id " + uid));
		GroupPipeline existingPipeline = groupPipelineRepository.findByProjectGroup(projectGroup);

		if (existingPipeline != null) {
			throw new IllegalArgumentException("A pipeline already exists for the given project group");
		}
		if (groupPipelineRequest.getExportType() == null || groupPipelineRequest.getLoadType() == null
				|| groupPipelineRequest.getRecurrence() == null || groupPipelineRequest.getTime() == null) {
			throw new HttpRequestMethodNotSupportedException("Missing required fields");
		}

		GroupPipeline groupPipeline = GroupPipeline.builder().exportType(groupPipelineRequest.getExportType())
				.loadType(groupPipelineRequest.getLoadType()).recurrence(groupPipelineRequest.getRecurrence())
				.projectGroup(projectGroup).exportFileName(groupPipelineRequest.getExportFileName())
				.intimationList(groupPipelineRequest.getIntimationList()).time(groupPipelineRequest.getTime())
				.monthlyDays(groupPipelineRequest.getMonthlyDays()).weeklyDays(groupPipelineRequest.getWeeklyDays())
				.build();

		groupPipeline = groupPipelineRepository.save(groupPipeline);
		return getGroupPipelineResponse(groupPipeline);
	}

	private GroupPipelineResponse getGroupPipelineResponse(GroupPipeline groupPipeline) {
		return GroupPipelineResponse.builder().uid(groupPipeline.getUid())
				.groupUid(groupPipeline.getProjectGroup().getUid()).loadType(groupPipeline.getLoadType())
				.exportType(groupPipeline.getExportType()).recurrence(groupPipeline.getRecurrence())
				.exportFileName(groupPipeline.getExportFileName()).intimationList(groupPipeline.getIntimationList())
				.time(groupPipeline.getTime()).monthlyDays(groupPipeline.getMonthlyDays())
				.weeklyDays(groupPipeline.getWeeklyDays()).build();
	}

	/**
	 * 
	 * @param Get by id
	 * @return
	 */

	public GroupPipelineResponse getGroupPipeline(Long id) {
		GroupPipeline groupPipeline = groupPipelineRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Group pipeline not found with id " + id));
		if (groupPipeline != null) {
			return getGroupPipelineResponse(groupPipeline);
		} else {
			String errorMessage = "Pipeline with ID " + id + " not found";
			throw new NullPointerException(errorMessage);
		}
	}

	/**
	 * 
	 * @param get pipeline by projectgroup uid
	 * @return
	 */
	public GroupPipelineResponse findByProjectGroup(Long uid) {
		ProjectGroup projectGroup = projectGroupRepository.findById(uid)
				.orElseThrow(() -> new ResourceNotFoundException("Project group not found with id " + uid));

		GroupPipeline pipeline = groupPipelineRepository.findByProjectGroup(projectGroup);
		if (pipeline == null) {
			throw new ResourceNotFoundException("Pipeline not found for project group with id " + uid);
		}

		return getGroupPipelineResponse(pipeline);
	}

	/**
	 * 
	 * @param update               by id
	 * @param groupPipelineRequest
	 * @return
	 */

	public GroupPipelineResponse updateGroupPipeline(Long id, GroupPipelineRequest groupPipelineRequest) {
		GroupPipeline groupPipeline = groupPipelineRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Group Pipeline not found with id " + id));
		
		
		groupPipeline = GroupPipeline.builder().uid(groupPipeline.getUid())
				.exportType(groupPipelineRequest.getExportType()).loadType(groupPipelineRequest.getLoadType())
				.recurrence(groupPipelineRequest.getRecurrence()).projectGroup(groupPipeline.getProjectGroup())
				.exportFileName(groupPipelineRequest.getExportFileName()).intimationList(groupPipelineRequest.getIntimationList())
				.time(groupPipelineRequest.getTime()).monthlyDays(groupPipelineRequest.getMonthlyDays())
				.weeklyDays(groupPipelineRequest.getWeeklyDays()).build();

		groupPipeline = groupPipelineRepository.save(groupPipeline);

		GroupPipelineResponse groupPipelineResponse = GroupPipelineResponse.builder().uid(groupPipeline.getUid())
				.groupUid(groupPipeline.getProjectGroup().getUid())
				.exportType(groupPipeline.getExportType()).loadType(groupPipeline.getLoadType())
				.recurrence(groupPipeline.getRecurrence()).exportFileName(groupPipeline.getExportFileName())
				.intimationList(groupPipeline.getIntimationList()).time(groupPipeline.getTime())
				.monthlyDays(groupPipeline.getMonthlyDays())
				.weeklyDays(groupPipeline.getWeeklyDays()).build();

		return groupPipelineResponse;
	}

	/**
	 * 
	 * @param Delete by id
	 */
	 
	  @Transactional
	  public void deleteGroupPipeline(Long id) {
	    GroupPipeline groupPipeline = groupPipelineRepository.findById(id)
	      .orElseThrow(() -> new ResourceNotFoundException("Group pipeline not found with id " + id));
	    	    ProjectGroup projectGroup = groupPipeline.getProjectGroup();
	    projectGroup.setGroupPipeline(null);
	    	    groupPipelineRepository.delete(groupPipeline);
	  }
}
