package it.uniroma3.siw.model;

import java.util.List;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "surname"}))
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Name cannot be blank")
	private String name;
	
	@NotBlank(message = "Surname cannot be blank")
	private String surname;
	
	@NotBlank(message = "Phone number cannot be blank")
	//@Pattern(regexp = "[0-9]", message = "Invalid phone number")
	@Column(unique = true)
	private String phoneNumber;
	
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Please provide a valid email address")
	@Column(unique = true)
	private String email;
	
	@ManyToOne
    private Specialization specialization;
	
	@ManyToOne
	private HealthFacility healthFacility;
	
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "Doctor_id")
    private List<Availability> availabilities;
    
    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.ALL})
    private List<Appointment> appointments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public HealthFacility getHealthFacility() {
		return healthFacility;
	}

	public void setHealthFacility(HealthFacility healthFacility) {
		this.healthFacility = healthFacility;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}
    
    
}
