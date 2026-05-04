package com.amka.sims.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amka.sims.entity.Subject;
import com.amka.sims.service.SubjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

	private final SubjectService subjectService;

	public SubjectController(SubjectService service) {
		this.subjectService = service;
	}

	@PostMapping("/createSubject")
	public ResponseEntity<Subject> createSubject(@Valid @RequestBody Subject subject) {
		Subject sub = subjectService.createSubject(subject);
		return ResponseEntity.status(201).body(sub);
	}

}
