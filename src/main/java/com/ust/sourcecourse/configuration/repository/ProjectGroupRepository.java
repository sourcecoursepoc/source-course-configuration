package com.ust.sourcecourse.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ust.sourcecourse.configuration.entity.ProjectGroup;
@Repository
public interface ProjectGroupRepository extends JpaRepository<ProjectGroup, Long> {

	List<ProjectGroup> findByProjectUid(Long uid);

	ProjectGroup findByUid(Long groupUid);

	@Query("SELECT pg FROM ProjectGroup pg JOIN pg.tags t WHERE LOWER(t) = LOWER(:tag)")
	List<ProjectGroup> retrieveByTag(@Param("tag") String tag);


}
