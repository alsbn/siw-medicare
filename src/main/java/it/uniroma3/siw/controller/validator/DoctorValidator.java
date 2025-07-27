package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Doctor;
import it.uniroma3.siw.service.DoctorService;

@Component
public class DoctorValidator implements Validator {
	
	@Autowired
	private DoctorService doctorService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Doctor doctor = (Doctor)o;
		if (doctor.getEmail()!=null && doctorService.existsByEmail(doctor.getEmail()) ||
				doctor.getPhoneNumber()!=null && doctorService.existsByPhoneNumber(doctor.getPhoneNumber())) {
			errors.reject("doctor.duplicate");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Doctor.class.equals(aClass);
	}
}