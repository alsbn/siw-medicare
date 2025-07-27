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
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Specialization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Field name cannot be blank")
	@Column(unique=true)
	private String name;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "Specialization_id")
    private List<VisitType> visitTypes;
	
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

	public List<VisitType> getVisitTypes() {
		return visitTypes;
	}

	public void setVisitTypes(List<VisitType> visitTypes) {
		this.visitTypes = visitTypes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, visitTypes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specialization other = (Specialization) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(visitTypes, other.visitTypes);
	}
	
	
	
}
