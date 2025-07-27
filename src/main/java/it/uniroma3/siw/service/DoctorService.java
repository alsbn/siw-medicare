package it.uniroma3.siw.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Doctor;
import it.uniroma3.siw.model.Specialization;
import it.uniroma3.siw.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	public Doctor findById(Long id) {
		return doctorRepository.findById(id).get();
	}
	
	public Doctor findByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

	public Iterable<Doctor> findAll() {
		return doctorRepository.findAll();
	}

	public void deleteById(Long id) {
		doctorRepository.deleteById(id);
	}

	public boolean existsByEmail(String email) {
		return doctorRepository.existsByEmail(email);
	}

	public List<Doctor> findBySpecialization(Specialization specialization) {
		return doctorRepository.findBySpecialization(specialization);
	}

	public boolean existsByPhoneNumber(String phoneNumber) {
		return doctorRepository.existsByPhoneNumber(phoneNumber);
	}

	public void saveDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
	}

}
