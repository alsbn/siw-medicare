package it.uniroma3.siw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Availability;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Doctor;
import it.uniroma3.siw.model.Specialization;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.VisitType;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.DoctorService;
import it.uniroma3.siw.service.SpecializationService;
import it.uniroma3.siw.service.VisitTypeService;

@Controller
public class SpecializationController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private SpecializationService specializationService;
	
	@Autowired
	private VisitTypeService visitTypeService;
	
	@Autowired
	private DoctorService doctorService;
	
	
	//PATIENT_ROLE dashboard:
    @GetMapping("/patient/specialization")
    public String patientDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	User user = credentials.getUser();
    	model.addAttribute("user", user);
    	model.addAttribute("specializations", specializationService.findAll());
    	return "patient/specializations.html";
    }
    
    @GetMapping("/patient/specialization/{id}/visitTypes")
    public String listVisitsBySpecialization(@PathVariable("id") Long id, Model model) {
        Specialization specialization = specializationService.findById(id);
        model.addAttribute("specialization", specialization);
        model.addAttribute("visitTypes", specialization.getVisitTypes());
        return "patient/specializationVisitTypes.html";
    }
	
    
    @GetMapping("/patient/bookingOptions/{specializationId}/{visitTypeId}")
    public String getBookingOptions(@PathVariable("specializationId") Long specializationId,
    								@PathVariable("visitTypeId") Long visitTypeId,
    								Model model) {
    	Specialization specialization = specializationService.findById(specializationId);
    	VisitType visitType = visitTypeService.findById(visitTypeId);

        // Trova tutti i dottori con la specializzazione richiesta
        List<Doctor> doctors = doctorService.findBySpecialization(specialization);

        // Costruisce mappa dottore -> slot disponibili
        Map<Doctor, List<Availability>> doctorAvailabilityMap = new HashMap<>();
        for (Doctor doctor : doctors) {
            List<Availability> availabilities = doctor.getAvailabilities();
            if (!availabilities.isEmpty()) {
                doctorAvailabilityMap.put(doctor, availabilities);
            }
        }
        
        model.addAttribute("visitType", visitType);
        model.addAttribute("doctorAvailabilityMap", doctorAvailabilityMap);

        return "patient/doctorsAvailabilities.html";
    }
    
}
