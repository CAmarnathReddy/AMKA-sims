package com.amka.sims.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Section name is required")
	@Column(nullable = false)
	private String name; // Example: "A", "B", "C"

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "standard_id", nullable = false)
	@JsonBackReference
	private Standard standard;

	@OneToOne
	@JoinColumn(name = "incharge_employee_id", unique = true)
	private Employee incharge;
}
