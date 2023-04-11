package com.ust.sourcecourse.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.GroupColumn;

public interface GroupColumnRepository extends JpaRepository<GroupColumn, Long> {

	List<GroupColumn> findByProjectGroupUid(Long groupUid);
}
