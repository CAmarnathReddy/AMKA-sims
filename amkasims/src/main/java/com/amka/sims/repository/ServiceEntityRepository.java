package com.amka.sims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amka.sims.entity.ServiceEntity;

public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Long> {

	@Query("""
		    SELECT DISTINCT se
		    FROM ServiceEntity se
		    LEFT JOIN FETCH se.locations
		    WHERE se.organization.organizationId = :orgId
		""")
	List<ServiceEntity> findAllServiceEntitiesByOrgId( @Param("orgId") Long organizationId);

}
