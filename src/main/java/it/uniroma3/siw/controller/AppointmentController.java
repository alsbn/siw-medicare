package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Appointment;
import it.uniroma3.siw.model.Availability;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Doctor;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.VisitType;
import it.uniroma3.siw.service.AppointmentService;
import it.uniroma3.siw.service.AvailabilityService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.DoctorService;
import it.uniroma3.siw.service.VisitTypeService;


@Controller
public class AppointmentController {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AvailabilityService availabilityService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private VisitTypeService visitTypeService;
	
	
	//DOCTOR_ROLE:
	
	@PostMapping("/doctor/cancelAppointment/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteById(id);
        return "redirect:/doctor/dashboard";
    }
    
    
    //PATIENT_ROLE:
	
	@GetMapping("/patient/appointment")
	public String getAppointments(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
		model.addAttribute("appointments", appointmentService.findByUser(user));
		return "patient/appointments.html";
	}
  	
	

	@PostMapping("/patient/newAppointment")
	public String newAppointment(@RequestParam Long visitTypeId,
								 @RequestParam Long doctorId,
								 @RequestParam Long availabilityId,
								 @AuthenticationPrincipal UserDetails userDetails) {
		
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
		VisitType visitType = visitTypeService.findById(visitTypeId);
		
        // Blocca doppia prenotazione della stessa visita
        boolean alreadyBooked = appointmentService.findByUser(user).stream()
                .anyMatch(a -> a.getVisitType().equals(visitType));
        if (alreadyBooked) {
            return "redirect:/patient/appointment?error=duplicate";
        }

        Doctor doctor = doctorService.findById(doctorId);

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment.setVisitType(visitType);
        
        Availability a = availabilityService.findById(availabilityId);
        appointment.setDate(a.getDate());
        appointment.setTime(a.getTime());

        appointmentService.save(appointment);
        availabilityService.delete(a);

        return "redirect:/patient/appointment?success";
	}
      
	@PostMapping("/patient/cancelAppointment/{id}")
	public String cancelAppointment(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
		Appointment appointment = appointmentService.findById(id);

		// Sicurezza: solo il paziente può cancellare i propri appuntamenti
		if (!appointment.getUser().equals(user)) {
			return "redirect:/patient/appointments?error=notallowed";
		}

		Availability availability = new Availability();
		availability.setDate(appointment.getDate());
		availability.setTime(appointment.getTime());
		availabilityService.save(availability);
		Doctor doctor= appointment.getDoctor();
		doctor.getAvailabilities().add(availability);
		appointmentService.delete(appointment);

		return "redirect:/patient/appointment?cancelled";
	}
    
}
