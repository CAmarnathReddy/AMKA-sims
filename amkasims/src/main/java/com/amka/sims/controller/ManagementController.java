package com.amka.sims.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amka.sims.entity.Location;
import com.amka.sims.entity.Organization;
import com.amka.sims.entity.ServiceEntity;
import com.amka.sims.service.ManagementService;

@RestController
@RequestMapping("/api/management")
public class ManagementController {

	private final ManagementService managementService;

	public ManagementController(ManagementService managementService) {
		super();
		this.managementService = managementService;
	}

	// Create org
	@PostMapping("/createorganization")
	public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
		return ResponseEntity.ok(managementService.createOrganization(organization));
	}

	@PostMapping("/createserviceentity")
	public ResponseEntity<?> createServiceEntity(@RequestBody ServiceEntity serviceEntity) {
		return managementService.createServiceEntity(serviceEntity);
	}

	@GetMapping("/getorgserviceentities")
	public List<ServiceEntity> getallOrgServiceEntities(@RequestParam("orgid") Long orgId) {
		return managementService.getallOrgServiceEntities(orgId);
	}
	
	@PostMapping("/createlocation")
	public ResponseEntity<?> createLocation(@RequestBody Location location) {
		return managementService.createLocation(location);
	}
	
	@GetMapping("/getselocations")
	public List<Location> getallSeLocation(@RequestParam("seid") Long seId) {
		return managementService.getallSeLocations(seId);
	}
	

}
