package com.amka.sims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amka.sims.entity.Employee;
import com.amka.sims.entity.Location;
import com.amka.sims.entity.Section;
import com.amka.sims.entity.Standard;
import com.amka.sims.exception.ResourceNotFoundException;
import com.amka.sims.repository.EmployeeRepository;
import com.amka.sims.repository.LocationRepository;
import com.amka.sims.repository.SectionRepository;
import com.amka.sims.repository.StandardRepository;

@Service
public class StandardService {

	private final StandardRepository standardRepository;
	private final SectionRepository sectionRepository;
	private final EmployeeRepository employeeRepository;
	private final LocationRepository locationRepository;

	public StandardService(StandardRepository standardRepository, SectionRepository sectionRepository,
			EmployeeRepository employeeRepository, LocationRepository locationRepository) {
		this.standardRepository = standardRepository;
		this.sectionRepository = sectionRepository;
		this.employeeRepository = employeeRepository;
		this.locationRepository = locationRepository;
	}

	// related to standard
	public Standard createStandard(Standard standard) {

		// Ensure incharge employees exist before saving
		if (standard.getSections() != null) {
			System.out.println("inside sections");
			for (Section section : standard.getSections()) {
				if (section.getIncharge() != null) {
					String empId = section.getIncharge().getId();
					Employee employee = employeeRepository.findById(empId)
							.orElseThrow(() -> new RuntimeException("Employee not found with id " + empId));
					section.setIncharge(employee);
				}
				section.setStandard(standard);
			}
		}
		Optional<Location> loc = locationRepository.findById(standard.getLocation().getLocationId());
		if (loc != null) {
			standard.setLocation(loc.get());
		}

		return standardRepository.save(standard);

	}

	public List<Standard> getAllStandards() {
		return standardRepository.findAll();
	}

	public Standard getStandardById(Long id) {
		return standardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Standard not found with id " + id));
	}

	public Standard updateStandard(Long id, Standard updated) {
		Standard existing = getStandardById(id);
		existing.setName(updated.getName());
		return standardRepository.save(existing);
	}

	public void deleteStandard(Long id) {
		standardRepository.delete(getStandardById(id));
	}

	// related to section

	public Section createSection(Long standardId, String empId, String sectionName) {
		// Find Standard
		Section section = new Section();
		Standard standard = standardRepository.findById(standardId)
				.orElseThrow(() -> new RuntimeException("Standard not found with id " + standardId));

		if (empId != null && !empId.isBlank()) {
			// Find Employee
			Employee employee = employeeRepository.findById(empId)
					.orElseThrow(() -> new RuntimeException("Employee not found with id " + empId));
			section.setIncharge(employee);
		}

		section.setStandard(standard);
		section.setName(sectionName);

		return sectionRepository.save(section);
	}

	public Section updateSection(Long sectionId, String sectionName, Long standardId, String empId) {
		Section section = sectionRepository.findById(sectionId)
				.orElseThrow(() -> new RuntimeException("Section not found with id " + sectionId));

		// update name
		if (sectionName != null && !sectionName.isBlank() && !sectionName.equals(section.getName())) {
			section.setName(sectionName);
			System.out.println("entered into section set");
		}

		// update incharge if new incharge have been updated

		if (empId != null && !empId.isBlank() && section.getIncharge() == null) {
			Employee emp = employeeRepository.findById(empId)
					.orElseThrow(() -> new RuntimeException("Employee not found with id " + empId));
			section.setIncharge(emp);
		} else if (empId != null && !empId.isBlank() && !section.getIncharge().getId().equals(empId)) {
			Employee emp = employeeRepository.findById(empId)
					.orElseThrow(() -> new RuntimeException("Employee not found with id " + empId));
			section.setIncharge(emp);
		}

		// 🚫 DO NOT overwrite standard unless explicitly requested
		// If needed, allow standard change like this:
		if (standardId != null && standardId != null) {
			Standard std = standardRepository.findById(standardId)
					.orElseThrow(() -> new RuntimeException("Standard not found"));
			section.setStandard(std);
		}

		return sectionRepository.save(section);
	}

	public List<Section> getAllSections() {
		return sectionRepository.findAll();
	}

	public Section getSectionById(Long id) {
		return sectionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Section not found with id " + id));
	}

	public void deleteSection(Long id) {
		sectionRepository.delete(getSectionById(id));
	}

}
