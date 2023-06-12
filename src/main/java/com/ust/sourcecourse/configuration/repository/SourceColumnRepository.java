package com.ust.sourcecourse.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ust.sourcecourse.configuration.entity.SourceColumn;

public interface SourceColumnRepository extends JpaRepository<SourceColumn, Long> {

	@Query("SELECT sc FROM SourceColumn sc JOIN sc.tags c WHERE LOWER(c) = LOWER(:tag)")
	List<SourceColumn> retrieveByTag(@Param("tag") String tag);
}
