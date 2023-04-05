package com.ust.sourcecourse.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.sourcecourse.configuration.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
