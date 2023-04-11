package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.SourceColumn;

public interface SourceColumnRepository extends JpaRepository<SourceColumn, Long> {

}
