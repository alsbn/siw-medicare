package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Availability;
import it.uniroma3.siw.model.Doctor;
import it.uniroma3.siw.service.AvailabilityService;
import it.uniroma3.siw.service.DoctorService;
import jakarta.validation.Valid;


@Controller
public class AvailabilityController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private AvailabilityService availabilityService;


	//DOCTOR_ROLE:

	// Form nuova disponibilità
	@GetMapping("/doctor/formNewAvailability")
	public String formNewAvailability(Model model) {
		model.addAttribute("availability", new Availability());
		return "doctor/formNewAvailability.html";
	}

	// Salvataggio disponibilità
	@PostMapping("/doctor/newAvailability")
	public String newAvailability(@Valid @ModelAttribute("availability") Availability availability,
								  BindingResult availabilityBindingResult,
								  @AuthenticationPrincipal UserDetails userDetails) {
		
		if(!availabilityBindingResult.hasErrors()) {
			Doctor doctor = doctorService.findByEmail(userDetails.getUsername());
			availabilityService.save(availability);
			doctor.getAvailabilities().add(availability);
			doctorService.saveDoctor(doctor);
			return "redirect:/doctor/dashboard";
		}
		return "redirect:/doctor/formNewAvailability?error";
	}

	// Cancellazione disponibilità
	@PostMapping("/doctor/deleteAvailability/{id}")
	public String deleteAvailability(@PathVariable Long id /*,@AuthenticationPrincipal UserDetails userDetails*/) {
		
		
		availabilityService.deleteById(id);
		
		return "redirect:/doctor/dashboard?availabilityDeleted";
	}
}
