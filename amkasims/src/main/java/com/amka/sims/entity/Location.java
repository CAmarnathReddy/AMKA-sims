package com.amka.sims.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "location")
@Data
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long locationId;

	private String locationName;

	private String coresspondingAddress;

	private String permanentAddress;

	private String email;

	private String phoneNumber;

	// Many locations -> one service
	@ManyToOne
	@JoinColumn(name = "se_id")
	@JsonIgnoreProperties("locations")
	private ServiceEntity serviceEntity;

}
