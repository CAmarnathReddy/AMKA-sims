package com.amka.sims.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "organization")
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long organizationId;

	private String organizationName;

	private String organizationType;

	private String email;

	private String phoneNumber;

	private String coresspondingAddress;
	
	private String permanentAddress;

	// One organization -> many services
	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("organization")
	private List<ServiceEntity> serviceEntities;

}
