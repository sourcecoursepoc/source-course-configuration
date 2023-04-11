package com.ust.sourcecourse.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.SourceTable;
import com.ust.sourcecourse.configuration.request.SourceTableRequest;

public interface SourceTableRepository extends JpaRepository<SourceTable, Long> {


	List<SourceTable> findByUidIn(List<Long> sourceTableUids);

}
