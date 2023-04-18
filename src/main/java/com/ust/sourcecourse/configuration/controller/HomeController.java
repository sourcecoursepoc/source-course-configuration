package com.ust.sourcecourse.configuration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sourcecourse.configuration.request.HomeRequest;
import com.ust.sourcecourse.configuration.response.DBTableColumn;
import com.ust.sourcecourse.configuration.response.HomePageResponse;
import com.ust.sourcecourse.configuration.service.HomeService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private HomeService homeService;

	@GetMapping
	public ResponseEntity<List<HomePageResponse>> getHomePageDetails() {
		return ResponseEntity.ok(homeService.getHomePageDetails());
	}


	@GetMapping("/tags/columns{uid}")
	public ResponseEntity<List<String>> getDescriptionAndTags1(@PathVariable Long uid) {
		List<String> homePageResponse = homeService.getDescriptionAndTags(uid);
		return ResponseEntity.ok(homePageResponse);
	}

	@GetMapping("/tags/tables{uid}")
	public ResponseEntity<List<String>> getDescriptionAndTags(@PathVariable Long uid) {
		List<String> homePageResponse = homeService.getDescriptionAndTags(uid);
		return ResponseEntity.ok(homePageResponse);
	}

	@PutMapping("/tags/columns{uid}")
	public ResponseEntity<DBTableColumn> updateDescriptionAndTags1(@PathVariable Long uid,
			@RequestBody HomeRequest request) {
		DBTableColumn homePageResponse = homeService.UpdateDescriptionandTags(uid, request);
		return ResponseEntity.ok(homePageResponse);
	}

	@PutMapping("/tags/tables{uid}")
	public ResponseEntity<DBTableColumn> updateDescriptionAndTags11(@PathVariable Long uid,
			@RequestBody HomeRequest request) {
		DBTableColumn homePageResponse = homeService.UpdateDescriptionandTags(uid, request);
		return ResponseEntity.ok(homePageResponse);
	}

	@DeleteMapping("/tags/columns/{uid}")
	public ResponseEntity<Void> deleteColumnTags(@PathVariable Long uid) {
		homeService.deleteColumnTags(uid);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/tags/tables/{uid}")
	public ResponseEntity<Void> deleteTableTags(@PathVariable Long uid) {
		homeService.deleteTableTags(uid);
		return ResponseEntity.noContent().build();
	}

}
