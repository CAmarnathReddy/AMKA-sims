package com.amka.sims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amka.sims.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
