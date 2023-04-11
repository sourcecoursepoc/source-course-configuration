package com.ust.sourcecourse.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.GroupColumn;
import com.ust.sourcecourse.configuration.mapper.RequestDataMapper;
import com.ust.sourcecourse.configuration.mapper.ResponseDataMapper;
import com.ust.sourcecourse.configuration.repository.GroupColumnRepository;
import com.ust.sourcecourse.configuration.request.ColumnData;
import com.ust.sourcecourse.configuration.response.ColumnInfo;

@Service
public class GroupColumnService {

	@Autowired
	private GroupColumnRepository groupColumnRepository;

	@Autowired
	private ResponseDataMapper responseDataMapper;

	@Autowired
	private RequestDataMapper requestDataMapper;

	public List<ColumnInfo> getColumnsByGroup(Long groupUid) {
		List<GroupColumn> groupColumns = groupColumnRepository.findByProjectGroupUid(groupUid);
		return responseDataMapper.getColumnInfos(groupColumns);
	}

	public ColumnInfo updateColumn(Long columnUid, ColumnData columnData) {
		GroupColumn groupColumn = groupColumnRepository.findById(columnUid).orElseThrow();
		requestDataMapper.updateGroupColumn(columnData, groupColumn);
		groupColumn = groupColumnRepository.save(groupColumn);
		return responseDataMapper.getGroupColumnInfo(groupColumn);
	}

	public void deleteColumn(Long columnUid) {
		groupColumnRepository.deleteById(columnUid);
	}
}
