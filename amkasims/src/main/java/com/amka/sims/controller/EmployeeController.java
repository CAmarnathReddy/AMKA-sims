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
import org.springframework.web.bind.annotation.RestController;

import com.amka.sims.entity.Employee;
import com.amka.sims.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	// Create Employee
	@PostMapping("/createemployee")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(service.createEmployee(employee));
	}

	// Get all Employees
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok(service.getAllEmployees());
	}

	// Get Employee by ID
	@GetMapping("/getemployeebyid/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.getEmployeeById(id));
	}

	// Update Employee
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
		return ResponseEntity.ok(service.updateEmployee(id, employee));
	}

	// Delete Employee
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
		service.deleteEmployee(id);
		return ResponseEntity.ok("Employee deleted successfully");
	}

}
