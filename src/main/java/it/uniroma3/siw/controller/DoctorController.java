package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validator.DoctorValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Doctor;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.DoctorService;
import it.uniroma3.siw.service.HealthFacilityService;
import it.uniroma3.siw.service.SpecializationService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

@Controller
public class DoctorController {

	@Autowired 
	private DoctorService doctorService;

	@Autowired 
	private SpecializationService specializationService;
	
	@Autowired 
	private HealthFacilityService healthFacilityService;
	
	@Autowired 
	private CredentialsService credentialsService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private DoctorValidator doctorValidator;

	
	//ADMIN_ROLE:
	
	@GetMapping("/admin/formNewDoctor")
	public String formNewDoctor(Model model) {
		// se ti serve la lista delle specializzazioni per una <select>
		model.addAttribute("doctorCredentials", new Credentials());
		model.addAttribute("specializations", specializationService.findAll());
        model.addAttribute("healthFacilities", healthFacilityService.findAll());
		return "admin/formNewDoctor.html";        // ← il template HTML
	}


	@PostMapping("/admin/newDoctor")
	public String newDoctor(@RequestParam String name,
							@RequestParam String surname,
							@RequestParam String phone,
							@RequestParam Long specializationId,
							@RequestParam Long healthFacilityId,
							@Valid @ModelAttribute("doctorCredentials") Credentials doctorCredentials,
							BindingResult credentialsBindingResult,
							BindingResult bindingResult,
							Model model) {

		if (!credentialsBindingResult.hasErrors()) {
			
			Doctor doctor = new Doctor();
			doctor.setEmail(doctorCredentials.getUsername());
			doctor.setPhoneNumber(phone);
			doctorValidator.validate(doctor, bindingResult);
			
			if(!bindingResult.hasErrors()) {
				User user = new User();
				user.setName(name);
				user.setSurname(surname);
				userService.saveUser(user);
				
				doctorCredentials.setUser(user);
				credentialsService.saveDoctorCredentials(doctorCredentials);
				
				doctor.setName(name);
				doctor.setSurname(surname);
				doctor.setSpecialization(specializationService.findById(specializationId));
				doctor.setHealthFacility(healthFacilityService.findById(healthFacilityId));
				doctorService.saveDoctor(doctor);
				
				return "redirect:/admin/doctor?success";
			}
			return "redirect:/admin/formNewDoctor?errorPhoneMail";
		}
		return "redirect:/admin/formNewDoctor?errorPassword";
	}


	@PostMapping("/admin/deleteDoctor/{id}")
	public String deleteDoctor(@PathVariable("id") Long id) {
		Doctor doctor = doctorService.findById(id);
		Credentials credentials = credentialsService.getCredentials(doctor.getEmail());
		User user = credentials.getUser();
		
		userService.delete(user);
		doctorService.deleteById(id);
		credentialsService.delete(credentials);
		return "redirect:/admin/doctor?deleted";
	}

	//elenco dottori
	@GetMapping("/admin/doctor")
	public String getDoctors(Model model) {
		model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("specializations", specializationService.findAll());
        model.addAttribute("healthFacilities", healthFacilityService.findAll());
		return "admin/doctors.html";
	}
	
	//Salva le modifiche 
    @PostMapping("/admin/updateDoctor/{id}")
    public String updateDoctor(@PathVariable("id") Long id,
    						   @RequestParam String name,
    						   @RequestParam String surname,
    						   @RequestParam String email,
    						   @RequestParam String phone,
    						   @RequestParam Long specializationId,
    						   @RequestParam Long healthFacilityId) {
        
        Doctor doctor = doctorService.findById(id);
        Credentials credentials = credentialsService.getCredentials(doctor.getEmail());  
        User user=credentials.getUser();
        
        doctor.setName(name);
        user.setName(name);  
        
        doctor.setSurname(surname);
        user.setSurname(surname);
        
        userService.saveUser(user);
        
        doctor.setSpecialization(specializationService.findById(specializationId));
        doctor.setHealthFacility(healthFacilityService.findById(healthFacilityId));
        
        if(!doctorService.existsByPhoneNumber(phone))
        	doctor.setPhoneNumber(phone);
             
        if(!doctorService.existsByEmail(email) && credentialsService.getCredentials(email)==null) {
        	 credentials.setUsername(email);
        	 credentialsService.saveCredentials(credentials);
        	 doctor.setEmail(email);
        }        
        doctorService.saveDoctor(doctor);
        
        return "redirect:/admin/doctor";
    }
    

	//DOCTOR_ROLE:

	//Aggiornamento profilo: solo specializzazione
	@PostMapping("/doctor/{id}/updatePhone")
	public String updateProfile(@PathVariable("id") Long id,
								@RequestParam String phone) {
		Doctor doctor = doctorService.findById(id);//.orElseThrow();
		doctor.setPhoneNumber(phone);
		doctorService.saveDoctor(doctor);

		return "redirect:/doctor/dashboard?profileUpdated";
	}
}
