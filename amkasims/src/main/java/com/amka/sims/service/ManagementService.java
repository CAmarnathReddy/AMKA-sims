package com.amka.sims.service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amka.sims.entity.Location;
import com.amka.sims.entity.Organization;
import com.amka.sims.entity.ServiceEntity;
import com.amka.sims.repository.LocationRepository;
import com.amka.sims.repository.OrganizationRepository;
import com.amka.sims.repository.ServiceEntityRepository;

@Service
public class ManagementService {

	private final OrganizationRepository organizationRepository;
	private final ServiceEntityRepository serviceEntityRepository;
	private final LocationRepository locationRepository;

	public ManagementService(OrganizationRepository organizationRepository,
			ServiceEntityRepository serviceEntityRepository, LocationRepository locationRepository) {
		super();
		this.organizationRepository = organizationRepository;
		this.serviceEntityRepository = serviceEntityRepository;
		this.locationRepository = locationRepository;
	}

	public @Nullable Organization createOrganization(Organization organization) {
		return organizationRepository.save(organization);
	}

	public ResponseEntity<?> createServiceEntity(ServiceEntity serviceEntity) {
		Optional<Organization> org = organizationRepository
				.findById(serviceEntity.getOrganization().getOrganizationId());

		if (org.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Organization with ID " + serviceEntity.getOrganization().getOrganizationId() + " not found");
		}

		// Set managed organization object
		serviceEntity.setOrganization(org.get());

		// Save service entity
		ServiceEntity savedEntity = serviceEntityRepository.save(serviceEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	public List<ServiceEntity> getallOrgServiceEntities(Long orgId) {
		return serviceEntityRepository.findAllServiceEntitiesByOrgId(orgId);
	}

	public ResponseEntity<?> createLocation(Location location) {
		Optional<ServiceEntity> serviceEntity = serviceEntityRepository
				.findById(location.getServiceEntity().getServiceEntityId());

		if (serviceEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Service Entity with ID " + location.getServiceEntity().getServiceEntityId() + " not found");
		}

		// Set managed service entity object
		location.setServiceEntity(serviceEntity.get());

		// Save service entity
		Location loc = locationRepository.save(location);

		return ResponseEntity.status(HttpStatus.CREATED).body(loc);
	}

	public List<Location> getallSeLocations(Long seId) {
		// TODO Auto-generated method stub
		return locationRepository.findLocationByServiceEntityId(seId);
	}
	
	
	
	
	

}
