package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.HealthFacility;

public interface HealthFacilityRepository extends CrudRepository<HealthFacility, Long> {

	HealthFacility findByName(String name);
	
}
