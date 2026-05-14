package com.amka.sims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amka.sims.entity.Employee;
import com.amka.sims.entity.ServiceEntity;
import com.amka.sims.entity.Subject;
import com.amka.sims.repository.EmployeeRepository;
import com.amka.sims.repository.ServiceEntityRepository;
import com.amka.sims.repository.SubjectRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository repository;
	private final SubjectRepository subjectRepository;
	private final ServiceEntityRepository serviceEntityRepository;

	public EmployeeService(EmployeeRepository repository, SubjectRepository subjectRepository,ServiceEntityRepository serviceEntityRepository) {
		this.repository = repository;
		this.subjectRepository = subjectRepository;
		this.serviceEntityRepository = serviceEntityRepository;
	}

	// Create

	public Employee createEmployee(Employee employee) {

		List<Subject> subjects = subjectRepository.findAllById(employee.getSubjectIds());
		Optional<ServiceEntity> serviceEntity = serviceEntityRepository.findById(employee.getServiceEntity().getServiceEntityId());
		if (serviceEntity!=null) {
			employee.setServiceEntity(serviceEntity.get());
		}
		employee.setSubjects(subjects);
		return repository.save(employee);
	}

	// Read all
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	// Read one
	public Employee getEmployeeById(String id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
	}

	// Update
	public Employee updateEmployee(String id, Employee updatedEmployee) {
		Employee existing = getEmployeeById(id);
		existing.setName(updatedEmployee.getName());
		existing.setEmail(updatedEmployee.getEmail());

		return repository.save(existing);
	}

	// Delete
	public void deleteEmployee(String id) {
		repository.deleteById(id);
	}

}
