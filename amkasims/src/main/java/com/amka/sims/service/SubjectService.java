package com.amka.sims.service;

import org.springframework.stereotype.Service;

import com.amka.sims.entity.Subject;
import com.amka.sims.repository.SubjectRepository;

@Service
public class SubjectService {

	private final SubjectRepository subjectRepository;

	public SubjectService(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	public Subject createSubject(Subject subject) {
		Subject sub = subjectRepository.save(subject);
		return sub;
	}

}
