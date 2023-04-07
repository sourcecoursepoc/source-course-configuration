package com.ust.sourcecourse.configuration.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ust.sourcecourse.configuration.entity.ColumnsEntity;


@Repository
public interface ColumnsRepository extends CrudRepository<ColumnsEntity, String> {

	public List<ColumnsEntity> findAll();

}
