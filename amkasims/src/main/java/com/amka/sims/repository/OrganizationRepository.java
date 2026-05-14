package com.amka.sims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amka.sims.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
