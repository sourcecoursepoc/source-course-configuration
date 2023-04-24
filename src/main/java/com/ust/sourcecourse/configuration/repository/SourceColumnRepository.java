package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.sourcecourse.configuration.entity.SourceColumn;

@Repository
public interface SourceColumnRepository extends JpaRepository<SourceColumn, Long> {

}
