package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Specialization;
import it.uniroma3.siw.repository.SpecializationRepository;

@Service
public class SpecializationService {
	
	@Autowired
	private SpecializationRepository specializationRepository;
	
	public Specialization findById(Long id) {
		return specializationRepository.findById(id).get();
	}

	public Iterable<Specialization> findAll() {
		return specializationRepository.findAll();
	}

	public Specialization findByName(String name) {
		return specializationRepository.findByName(name);
	}
	
}
