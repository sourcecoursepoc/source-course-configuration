package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.SourceTable;

public interface SourceTableRepository extends JpaRepository<SourceTable, Long> {


}
