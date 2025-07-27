package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class VisitType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Visit type name cannot be blank")
	private String name;
	
	@ManyToMany
	private List<HealthFacility> healthFacilities;

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

	public List<HealthFacility> getHealthFacilities() {
		return healthFacilities;
	}

	public void setHealthFacilities(List<HealthFacility> healthFacilities) {
		this.healthFacilities = healthFacilities;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VisitType other = (VisitType) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	
}
