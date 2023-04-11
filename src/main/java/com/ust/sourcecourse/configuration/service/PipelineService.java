package com.ust.sourcecourse.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.GroupPipeline;
import com.ust.sourcecourse.configuration.entity.ProjectGroup;
import com.ust.sourcecourse.configuration.mapper.RequestDataMapper;
import com.ust.sourcecourse.configuration.mapper.ResponseDataMapper;
import com.ust.sourcecourse.configuration.repository.GroupPiplelineRepository;
import com.ust.sourcecourse.configuration.repository.ProjectGroupRepository;
import com.ust.sourcecourse.configuration.request.PipelineData;
import com.ust.sourcecourse.configuration.response.PipelineInfo;

@Service
public class PipelineService {

	@Autowired
	private ProjectGroupRepository projectGroupRepository;

	@Autowired
	private ResponseDataMapper responseDataMapper;

	@Autowired
	private RequestDataMapper requestDataMapper;

	@Autowired
	private GroupPiplelineRepository groupPiplelineRepository;

	public PipelineInfo getPipelineById(Long uid) {
		GroupPipeline groupPipeline = groupPiplelineRepository.findById(uid).orElseThrow();
		return responseDataMapper.getPipelineInfo(groupPipeline);
	}

	public PipelineInfo getPipelineByGroup(Long groupUid) {
		GroupPipeline groupPipeline = groupPiplelineRepository.findByProjectGroupUid(groupUid);
		return responseDataMapper.getPipelineInfo(groupPipeline);
	}

	public PipelineInfo updatePipeline(Long pipelineUid, PipelineData pipelineData) {
		GroupPipeline groupPipeline = groupPiplelineRepository.findById(pipelineUid).orElseThrow();
		requestDataMapper.updatePipeline(pipelineData, groupPipeline);
		groupPipeline = groupPiplelineRepository.save(groupPipeline);
		return responseDataMapper.getPipelineInfo(groupPipeline);
	}

	public PipelineInfo setPipeline(Long groupUid, PipelineData pipelineData) {
		ProjectGroup projectGroup = projectGroupRepository.findById(groupUid).orElseThrow();
		GroupPipeline groupPipeline = requestDataMapper.getGroupPipeline(projectGroup, pipelineData);
		projectGroup.setGroupPipeline(groupPipeline);
		projectGroup = projectGroupRepository.save(projectGroup);
		return responseDataMapper.getPipelineInfo(projectGroup.getGroupPipeline());
	}
}
