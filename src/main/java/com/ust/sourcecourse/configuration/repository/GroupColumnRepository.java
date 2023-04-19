package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.sourcecourse.configuration.entity.GroupColumn;
@Repository
public interface GroupColumnRepository extends JpaRepository<GroupColumn, Long>{

}
