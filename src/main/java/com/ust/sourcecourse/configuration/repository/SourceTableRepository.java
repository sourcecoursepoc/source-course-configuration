package com.ust.sourcecourse.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.SourceTable;

public interface SourceTableRepository extends JpaRepository<SourceTable, Long> {

	List<String> getTags();




}
