package it.uniroma3.siw.repository;

import java.util.Collection;


import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Appointment;
import it.uniroma3.siw.model.User;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

	Collection<Appointment> findByUser(User user);
}
