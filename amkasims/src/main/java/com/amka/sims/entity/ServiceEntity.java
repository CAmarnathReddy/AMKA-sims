package com.amka.sims.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "serviceentity")
@Data
public class ServiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serviceEntityId;

	@Column(name = "serviceentityname")
	private String serviceEntityName;

	@Column(name = "correspondingaddress")
	private String correspondingAddress;

	@Column(name = "permanentaddress")
	private String permanentAddress;

	private String email;

	private String phoneNumber;

	private Boolean active;

	// Many services -> one organization
	@ManyToOne
	@JoinColumn(name = "org_id")
	@JsonIgnoreProperties("serviceEntities")
	private Organization organization;

	// One service -> many locations
	@OneToMany(mappedBy = "serviceEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("serviceEntity")
	private Set<Location> locations;

}
