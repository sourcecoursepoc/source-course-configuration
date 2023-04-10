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

@Service
public class SourceTableService {

	@Autowired
	private SourceTableRepository sourcetablerepo;
	private DataSourceRepository dataSourceRepository;

	public SourceTableResponse createProjectTable(SourceTableRequest tableRequest) {

		Optional<DataSource> optionalDataSource = dataSourceRepository.findById(tableRequest.getDataSourceUid());
		DataSource dataSource = optionalDataSource.get();

		SourceTable project = SourceTable.builder().name(tableRequest.getName())
				.description(tableRequest.getDescription())
				.dataSource(dataSource)
				.rowCount(tableRequest.getRowCount()).size(tableRequest.getSize()).minDate(tableRequest.getMinDate())
				.maxDate(tableRequest.getMaxDate()).yoyCount(tableRequest.getYoyCount())
				.momCount(tableRequest.getMomCount()).tags(tableRequest.getTags()).build();
		project = sourcetablerepo.save(project);
		return SourceTableResponse.builder().uid(project.getUid()).name(project.getName())
				.description(project.getDescription()).rowCount(project.getRowCount()).size(project.getSize())
				.minDate(project.getMinDate()).maxDate(project.getMaxDate()).yoyCount(project.getYoyCount())
				.momCount(project.getMomCount()).tags(project.getTags()).build();
	}

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
