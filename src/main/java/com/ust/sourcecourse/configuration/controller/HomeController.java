package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.response.HomePageResponse;
import com.ust.sourcecourse.configuration.service.HomeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private HomeService homeService;

	@GetMapping
	@Operation(summary = "Get Home Page Details", description = "Retrieve the details of the home page")
	public ResponseEntity<List<HomePageResponse>> getHomePageDetails() {
		return ResponseEntity.ok(homeService.getHomePageDetails());
	}

}