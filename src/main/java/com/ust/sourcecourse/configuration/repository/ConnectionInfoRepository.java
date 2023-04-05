package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.ConnectionInfo;

public interface ConnectionInfoRepository extends JpaRepository<ConnectionInfo, Long> {

}
