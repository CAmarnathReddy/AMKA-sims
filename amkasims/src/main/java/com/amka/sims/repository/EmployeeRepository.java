package com.amka.sims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amka.sims.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
