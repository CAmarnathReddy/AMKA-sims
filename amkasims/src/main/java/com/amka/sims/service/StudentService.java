package com.amka.sims.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amka.sims.entity.Student;
import com.amka.sims.exception.ResourceNotFoundException;
import com.amka.sims.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository repository;

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public Student createStudent(Student student) {
		return repository.save(student);
	}

	public List<Student> getAllStudents() {
		return repository.findAll();
	}

	public Student getStudentById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
	}

	public Student updateStudent(Long id, Student updated) {
		Student existing = getStudentById(id);
		existing.setName(updated.getName());
		existing.setAge(updated.getAge());
		existing.setEmail(updated.getEmail());
		existing.setCourse(updated.getCourse());
		return repository.save(existing);
	}

	public void deleteStudent(Long id) {
		Student existing = getStudentById(id);
		repository.delete(existing);
	}
}