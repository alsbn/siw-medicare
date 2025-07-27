package it.uniroma3.siw.model;

import java.time.LocalDate;


import java.time.LocalTime;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;

@Entity
public class Availability {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Future(message = "Date must be in the future")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private LocalDate date;
	
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime time;

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

	@Override
	public int hashCode() {
		return Objects.hash(date, id, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Availability other = (Availability) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && Objects.equals(time, other.time);
	}
	
}
