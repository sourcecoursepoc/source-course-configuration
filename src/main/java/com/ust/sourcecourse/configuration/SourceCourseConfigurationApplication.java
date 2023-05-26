package com.ust.sourcecourse.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SourceCourseConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SourceCourseConfigurationApplication.class, args);
	}

}
