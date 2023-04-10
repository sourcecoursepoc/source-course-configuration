package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.DataQuality;

public interface DataQualityRepository extends JpaRepository<DataQuality, Long>{

}
