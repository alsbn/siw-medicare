package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Availability;

public interface AvailabilityRepository extends CrudRepository<Availability, Long> {
}
