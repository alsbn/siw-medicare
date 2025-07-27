package it.uniroma3.siw.service;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Appointment;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public Appointment findById(Long id) {
		return appointmentRepository.findById(id).get();
	}
	
	public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void delete(Appointment appointment) {
        appointmentRepository.delete(appointment);
    }

	public void deleteById(Long id) {
		appointmentRepository.deleteById(id);
	}

	public Collection<Appointment> findByUser(User user) {
		return appointmentRepository.findByUser(user);
	}
    
}
