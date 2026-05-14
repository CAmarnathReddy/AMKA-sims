package com.amka.sims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amka.sims.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

	@Query("""
			    SELECT l
			    FROM Location l
			    JOIN FETCH l.serviceEntity se
			    WHERE se.serviceEntityId = :serviceEntityId
			""")

	List<Location> findLocationByServiceEntityId( @Param("serviceEntityId") Long seId);

}
