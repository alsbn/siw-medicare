package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Availability;
import it.uniroma3.siw.repository.AvailabilityRepository;

@Service
public class AvailabilityService {
	
	@Autowired
	private AvailabilityRepository availabilityRepository;
	
	public Availability findById(Long id) {
		return availabilityRepository.findById(id).get();
	}
	
	public void save(Availability availability) {
        availabilityRepository.save(availability);
    }

    public void delete(Availability availability) {
        availabilityRepository.delete(availability);
    }

	public void deleteById(Long id) {
		availabilityRepository.deleteById(id);
	}
}
