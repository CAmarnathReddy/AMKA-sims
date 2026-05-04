package com.amka.sims.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amka.sims.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
}
