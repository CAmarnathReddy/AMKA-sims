package com.amka.sims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amka.sims.entity.Standard;

public interface StandardRepository extends JpaRepository<Standard, Long> {
}