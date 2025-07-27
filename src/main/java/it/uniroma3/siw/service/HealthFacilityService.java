package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.HealthFacility;
import it.uniroma3.siw.repository.HealthFacilityRepository;

@Service
public class HealthFacilityService {
	
	@Autowired
	private HealthFacilityRepository healthFacilityRepository;
	
	public HealthFacility findById(Long id) {
		return healthFacilityRepository.findById(id).get();
	}

	public Iterable<HealthFacility> findAll() {
		return healthFacilityRepository.findAll();
	}

	public HealthFacility findByName(String name) {
		return healthFacilityRepository.findByName(name);
	}
}
