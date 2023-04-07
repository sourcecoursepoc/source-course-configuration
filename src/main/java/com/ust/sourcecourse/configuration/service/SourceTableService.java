package com.ust.sourcecourse.configuration.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.request.SourceTableRequest;
import com.ust.sourcecourse.configuration.response.SourceTableResponse;

@Service
public class SourceTableService {

	@Autowired
	private SourceTableRepository sourcetablerepo;

	/**
	 * 
	 * 
	 * @param tableRequest
	 * @return
	 */
	public SourceTableResponse createSourceTable(SourceTableRequest tableRequest) {
		SourceTable project = SourceTable.builder().name(tableRequest.getName())
				.description(tableRequest.getDescription()).build();
		project = sourcetablerepo.save(project);
		return SourceTableResponse.builder().uid(project.getUid()).name(project.getName())
				.description(project.getDescription()).build();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSourceTable(Long id) {
		Optional<SourceTable> existingTable = sourcetablerepo.findById(id);
		if (existingTable.isPresent()) {
			sourcetablerepo.delete(existingTable.get());
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	public Optional<SourceTable> getSourceTableById(Long id) {
		return sourcetablerepo.findById(id);
	}

	/**
	 * 
	 * @param id
	 * @param sourceTable
	 * @return
	 */

	public Optional<SourceTable> updateSourceTable(Long id, SourceTable sourceTable) {
		Optional<SourceTable> existingTable = sourcetablerepo.findById(id);
		if (existingTable.isPresent()) {
			SourceTable updateTable = sourcetablerepo.save(sourceTable);
			return Optional.of(updateTable);
		} else {
			return Optional.empty();
		}

	}

}
