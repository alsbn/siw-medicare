package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Doctor;
import it.uniroma3.siw.model.Specialization;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

	Doctor findByEmail(String email);

	boolean existsByEmail(String email);

	List<Doctor> findBySpecialization(Specialization specialization);

	boolean existsByPhoneNumber(String phoneNumber);
}
