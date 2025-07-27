package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Specialization;

public interface SpecializationRepository extends CrudRepository<Specialization, Long> {

	Specialization findByName(String name);
	
}
