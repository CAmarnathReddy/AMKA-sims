package com.amka.sims.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amka.sims.entity.Section;
import com.amka.sims.entity.Standard;
import com.amka.sims.service.StandardService;

@RestController
@RequestMapping("/api/standard")
public class StandardController {

	private final StandardService standardService;

	public StandardController(StandardService standardService) {
		this.standardService = standardService;
	}

	// Related Standard

	@PostMapping("/createStandard")
	public ResponseEntity<Standard> createStandard(@RequestBody Standard standard) {
		return ResponseEntity.ok(standardService.createStandard(standard));
	}

	@GetMapping("/getStandards")
	public ResponseEntity<List<Standard>> getAllStandards() {
		return ResponseEntity.ok(standardService.getAllStandards());
	}

	@GetMapping("/getStandardById/{id}")
	public ResponseEntity<Standard> getStandardById(@PathVariable Long id) {
		return ResponseEntity.ok(standardService.getStandardById(id));
	}

	@PutMapping("/updateStandard/{id}")
	public ResponseEntity<Standard> updateStandard(@PathVariable Long id, @RequestBody Standard standard) {
		return ResponseEntity.ok(standardService.updateStandard(id, standard));
	}

	@DeleteMapping("/deleteStandard/{id}")
	public ResponseEntity<Void> deleteStandard(@PathVariable Long id) {
		standardService.deleteStandard(id);
		return ResponseEntity.noContent().build();
	}

	// Related to Section

	@PostMapping("/createSection")
	public ResponseEntity<Section> createSection(@RequestParam("sectionname") String sectionName,
			@RequestParam("standardid") Long standardId, @RequestParam("empid") String empId) {
		return ResponseEntity.ok(standardService.createSection(standardId, empId, sectionName));
	}

	@PostMapping("/updatesection")
	public ResponseEntity<Section> updateSection(@RequestParam("sectionId") Long sectionId,
			@RequestParam("sectionname") String sectionName, @RequestParam("standardid") Long standardId,
			@RequestParam("empid") String empId) {
		return ResponseEntity.ok(standardService.updateSection(sectionId,sectionName, standardId, empId));

	}
}
