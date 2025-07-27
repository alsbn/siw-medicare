package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import it.uniroma3.siw.model.Appointment;
import it.uniroma3.siw.model.Availability;
import it.uniroma3.siw.model.Doctor;
import it.uniroma3.siw.service.DoctorService;


@Controller
public class UserController {
	
	@Autowired
	private DoctorService doctorService;
	
	
	//ADMIN_ROLE dashboard:
	@GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard.html";
    }
	
	
	//DOCTOR_ROLE dashboard:
    @GetMapping("/doctor/dashboard")
    public String doctorDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Doctor doctor = doctorService.findByEmail(userDetails.getUsername());
        List<Appointment> appointments = doctor.getAppointments();
        List<Availability> availabilities = doctor.getAvailabilities();

        model.addAttribute("doctor", doctor);
        model.addAttribute("appointments", appointments);
        model.addAttribute("availabilities", availabilities);

        return "doctor/dashboard.html";
    }
	
}
