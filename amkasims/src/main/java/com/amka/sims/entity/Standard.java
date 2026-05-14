package com.amka.sims.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "standards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Standard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Standard name is required")
	@Column(nullable = false, unique = true)
	private String name; // Example: "1st Standard", "2nd Standard"

	@OneToMany(mappedBy = "standard", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Section> sections = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "loc_id") 
	private Location location;

}
