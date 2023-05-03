package com.ust.sourcecourse.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ust.sourcecourse.configuration.entity.SourceTable;

public interface SourceTableRepository extends JpaRepository<SourceTable, Long> {

	@Query("SELECT st FROM SourceTable st JOIN st.tags t WHERE t = LOWER(:tag)")
	List<SourceTable> retrieveByTag(@Param("tag") String tag);

}
