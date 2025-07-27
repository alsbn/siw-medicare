package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime time;
	
	@ManyToOne
    private Doctor doctor;

    @ManyToOne
    private User user;
    
    @OneToOne
    private VisitType visitType;
    
    @OneToOne
    private HealthFacility healthFacility;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	public HealthFacility getHealthFacility() {
		return healthFacility;
	}

	public void setHealthFacility(HealthFacility healthFacility) {
		this.healthFacility = healthFacility;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, doctor, healthFacility, id, user, visitType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(date, other.date) && Objects.equals(doctor, other.doctor)
				&& Objects.equals(healthFacility, other.healthFacility) && Objects.equals(id, other.id)
				&& Objects.equals(user, other.user) && Objects.equals(visitType, other.visitType);
	}
	
}
