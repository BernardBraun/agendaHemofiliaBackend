package br.com.github.apihemofilia.apihemofilia.domain.entitys;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.github.apihemofilia.apihemofilia.enums.BleedTypeLocal;
import br.com.github.apihemofilia.apihemofilia.enums.Reason;
import br.com.github.apihemofilia.apihemofilia.enums.Treatment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "diary")
public class Diary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "infusion_date", nullable = false)
	private LocalDateTime infusionDate;
	
	@Column(name = "reason", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Reason reason;
	
	@Column(name = "bleed_type_local", nullable = false)
	@Enumerated(EnumType.ORDINAL)	
	private BleedTypeLocal bleedTypeLocal;

	@Column(name = "treatment", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Treatment treatment;
	
	@Column(name = "observation")
	private String observation;
	
	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "hemocenter_id")
	private Hemocenter hemocenter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getInfusionDate() {
		return infusionDate;
	}

	public void setInfusionDate(LocalDateTime infusionDate) {
		this.infusionDate = infusionDate;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}

	public BleedTypeLocal getBleedTypeLocal() {
		return bleedTypeLocal;
	}

	public void setBleedTypeLocal(BleedTypeLocal bleedTypeLocal) {
		this.bleedTypeLocal = bleedTypeLocal;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Hemocenter getHemocenter() {
		return hemocenter;
	}

	public void setHemocenter(Hemocenter hemocenter) {
		this.hemocenter = hemocenter;
	}

}
