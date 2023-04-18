
package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.ust.sourcecourse.configuration.entity.GroupColumn;
import com.ust.sourcecourse.configuration.entity.ProjectGroup;
import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.repository.ProjectGroupRepository;
import com.ust.sourcecourse.configuration.repository.SourceColumnRepository;
import com.ust.sourcecourse.configuration.request.ColumnsRequest;
import com.ust.sourcecourse.configuration.response.AttributesInfo;
import com.ust.sourcecourse.configuration.response.ColumnsResponse;
import com.ust.sourcecourse.configuration.response.SourceInfo;

@Service
public class ColumnsService {

	@Autowired
	private SourceColumnRepository columnsRepository;

	@Autowired
	private ProjectGroupRepository projectGroupRepository;
	public List<ColumnsResponse> getColumnInfo(Long groupId) {
		Optional<ProjectGroup> optional = projectGroupRepository.findById(groupId);
		if (optional.isPresent()) {
			ProjectGroup projectGroup = optional.get();
			return getColumnResponses(projectGroup);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Group with uid " + groupId + " not found");
		}

	}

	@Transactional
	public List<ColumnsResponse> saveData(Long groupId, List<ColumnsRequest> columnsRequests) {
		Optional<ProjectGroup> optional = projectGroupRepository.findById(groupId);

		if (optional.isPresent()) {
			ProjectGroup projectGroup = optional.get();
			List<GroupColumn> groupColumns = projectGroup.getGroupColumns();
			if (groupColumns == null) {
				groupColumns = new ArrayList<>();
			}

			for (ColumnsRequest columnsRequest : columnsRequests) {
				SourceColumn sourceColumn = columnsRepository.findById(columnsRequest.getSourceColumnUid())
						.orElseThrow();

				GroupColumn groupColumn = GroupColumn.builder().name(columnsRequest.getName())
						.notes(columnsRequest.getNotes()).type(columnsRequest.getType())
						.isPrimary(columnsRequest.isPrimary()).defaultValue(columnsRequest.getDefaultvalue())
						.prefix(columnsRequest.getPreffix()).suffix(columnsRequest.getSuffix())
						.projectGroup(projectGroup).sourceColumn(sourceColumn).build();

				groupColumns.add(groupColumn);
				projectGroup.setGroupColumns(groupColumns);
				projectGroup = projectGroupRepository.save(projectGroup);
			}
			return getColumnResponses(projectGroup);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Group with uid " + groupId + " not found");
		}

	}

	private List<ColumnsResponse> getColumnResponses(ProjectGroup projectGroup) {
		List<ColumnsResponse> columnsResponses = new ArrayList<>();
		for (GroupColumn groupColumn : projectGroup.getGroupColumns()) {
			AttributesInfo attributesInfo = new AttributesInfo();
			attributesInfo.setName(groupColumn.getName());
			attributesInfo.setNotes(groupColumn.getNotes());
			attributesInfo.setType(groupColumn.getType());
			attributesInfo.setPrimary(groupColumn.isPrimary());
			attributesInfo.setDefaultValue(groupColumn.getDefaultValue());
			attributesInfo.setPrefix(groupColumn.getPrefix());
			attributesInfo.setSuffix(groupColumn.getSuffix());
			SourceInfo sourceInfo = new SourceInfo(groupColumn.getSourceColumn().getName(),
					groupColumn.getSourceColumn().getUid());
			ColumnsResponse columnsResponse = new ColumnsResponse(groupColumn.getUid(), attributesInfo, sourceInfo);
			columnsResponses.add(columnsResponse);
		}
		return columnsResponses;
	}

	public ColumnsResponse updateColumn(Long groupId, Long columnId, ColumnsRequest columnsRequest) {
		Optional<ProjectGroup> optional = projectGroupRepository.findById(groupId);
		if (optional.isPresent()) {
	        ProjectGroup projectGroup = optional.get();
	        List<GroupColumn> groupColumns = projectGroup.getGroupColumns();
	        for(GroupColumn groupColumn : groupColumns)
	        if(groupColumn.getUid().equals(columnId)) {
	        	SourceColumn sourceColumn = columnsRepository.findById(columnsRequest.getSourceColumnUid())
	        			.orElseThrow();
	        	groupColumn.setName(columnsRequest.getName());
	        	groupColumn.setNotes(columnsRequest.getNotes());
	        	groupColumn.setType(groupColumn.getType());
	        	groupColumn.setPrimary(groupColumn.isPrimary());
	        	groupColumn.setDefaultValue(groupColumn.getDefaultValue());
	        	groupColumn.setSuffix(groupColumn.getSuffix());
	        	groupColumn.setPrefix(groupColumn.getPrefix());
	        	groupColumn.setSourceColumn(sourceColumn);
	        	
	        	projectGroup = projectGroupRepository.save(projectGroup);
	        	AttributesInfo attributesInfo = new AttributesInfo();
	        	attributesInfo.setName(groupColumn.getName());
	        	attributesInfo.setNotes(groupColumn.getNotes());
	        	attributesInfo.setType(groupColumn.getType());
	        	attributesInfo.setPrimary(groupColumn.isPrimary());
	        	attributesInfo.setDefaultValue(groupColumn.getDefaultValue());
	        	attributesInfo.setPrefix(groupColumn.getPrefix());
	        	attributesInfo.setSuffix(groupColumn.getSuffix());
	        	SourceInfo SourceInfo = new SourceInfo(groupColumn.getSourceColumn().getName(),groupColumn.getSourceColumn().getUid());
	        	
	        	ColumnsResponse columnsResponse = new ColumnsResponse(groupColumn.getUid(),attributesInfo,SourceInfo);
	        	
	        	return columnsResponse;
	        }else {
	    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Group with uid " + groupId + " not found");
	    		
	    	}
	}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "column with uid" + columnId+ "not found");		
}
	
	    
	    }
	

	
	

