package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.DataSource;

public interface DataSourceRepository extends JpaRepository<DataSource, Long> {

}
