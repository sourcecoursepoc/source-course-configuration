package com.ust.sourcecourse.configuration.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.SourceColumn;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.repository.SourceColumnRepository;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;

@Service
public class SourceDataService {

	@Autowired
	private SourceTableRepository sourceTableRepository;

	@Autowired
	private SourceColumnRepository sourceColumnRepository;

	public List<String> addTableTags(Long sourceTableUid, List<String> tags) {
		SourceTable sourceTable = sourceTableRepository.findById(sourceTableUid).orElseThrow();
		List<String> currentTags = sourceTable.getTags();
		ArrayList<String> updatedTags = getUpdatedTags(tags, currentTags);
		sourceTable.setTags(updatedTags);
		sourceTable = sourceTableRepository.save(sourceTable);
		return sourceTable.getTags();
	}

	public List<String> removeTableTags(Long sourceTableUid, List<String> tags) {
		SourceTable sourceTable = sourceTableRepository.findById(sourceTableUid).orElseThrow();
		List<String> currentTags = sourceTable.getTags();
		currentTags.removeAll(tags);
		sourceTable.setTags(currentTags);
		sourceTable = sourceTableRepository.save(sourceTable);
		return sourceTable.getTags();
	}

	public List<String> addColumnTags(Long sourceColumnUid, List<String> tags) {
		SourceColumn sourceColumn = sourceColumnRepository.findById(sourceColumnUid).orElseThrow();
		List<String> currentTags = sourceColumn.getTags();
		ArrayList<String> updatedTags = getUpdatedTags(tags, currentTags);
		sourceColumn.setTags(updatedTags);
		sourceColumn = sourceColumnRepository.save(sourceColumn);
		return sourceColumn.getTags();
	}

	public List<String> removeColumnTags(Long sourceColumnUid, List<String> tags) {
		SourceColumn sourceColumn = sourceColumnRepository.findById(sourceColumnUid).orElseThrow();
		List<String> currentTags = sourceColumn.getTags();
		currentTags.removeAll(tags);
		sourceColumn.setTags(currentTags);
		sourceColumn = sourceColumnRepository.save(sourceColumn);
		return sourceColumn.getTags();
	}

	private ArrayList<String> getUpdatedTags(List<String> tags, List<String> currentTags) {
		if (currentTags == null) {
			currentTags = new ArrayList<>();
		}
		Set<String> tagSet = new LinkedHashSet<>(currentTags);
		tagSet.addAll(tags);
		return new ArrayList<String>(tagSet);
	}

}
