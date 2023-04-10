package com.ust.sourcecourse.configuration.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.sourcecourse.configuration.entity.DataSource;
import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.repository.DataSourceRepository;
import com.ust.sourcecourse.configuration.repository.SourceTableRepository;
import com.ust.sourcecourse.configuration.request.SourceTableRequest;
import com.ust.sourcecourse.configuration.response.SourceTableResponse;

import jakarta.validation.Valid;

@Service
public class SourceTableService {

	@Autowired
	private SourceTableRepository sourcetablerepo;
		

	public boolean deleteSourceTable(Long id) {
		Optional<SourceTable> existingTable = sourcetablerepo.findById(id);
		if (existingTable.isPresent()) {
			sourcetablerepo.delete(existingTable.get());
			return true;
		} else {
			return false;
		}

	}

	public Optional<SourceTable> getSourceTableById(Long id) {
		return sourcetablerepo.findById(id);
	}

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
