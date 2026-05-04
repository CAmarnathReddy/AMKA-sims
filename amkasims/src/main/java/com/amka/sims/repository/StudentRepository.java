package com.amka.sims.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amka.sims.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByEmail(String email);
}