package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.VisitType;
import it.uniroma3.siw.repository.VisitTypeRepository;

@Service
public class VisitTypeService {
	
	@Autowired
	private VisitTypeRepository visitTypeRepository;
	
	public VisitType findById(Long id) {
		return visitTypeRepository.findById(id).get();
	}

	public Iterable<VisitType> findAll() {
		return visitTypeRepository.findAll();
	}

}
