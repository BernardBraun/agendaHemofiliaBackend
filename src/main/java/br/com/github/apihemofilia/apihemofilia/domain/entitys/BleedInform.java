package br.com.github.apihemofilia.apihemofilia.domain.entitys;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.function.Function;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.BleedInformationDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bleed_inform")
public class BleedInform implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bleed_date", nullable = false)
	private LocalDate bleedDate;

	@Column(name = "bleed_local", nullable = false, length = 80)
	private String bleedLocal;

	@Column(name = "bleed_treatment", nullable = false)
	private String bleedTreatment;

	@Column(name = "observation")
	private String observation;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	public BleedInform() {
	}

	public BleedInform(final BleedInformationDto dto, Function<BleedInform, Person> getPerson) {
		this.bleedDate = dto.bleedDate();
		this.bleedLocal = dto.bleedLocal();
		this.bleedTreatment = dto.bleedTreatment();
		this.observation = dto.observation();
		this.person = getPerson.apply(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getBleedDate() {
		return bleedDate;
	}

	public void setBleedDate(LocalDate bleedDate) {
		this.bleedDate = bleedDate;
	}

	public String getBleedLocal() {
		return bleedLocal;
	}

	public void setBleedLocal(String bleedLocal) {
		this.bleedLocal = bleedLocal;
	}

	public String getBleedTreatment() {
		return bleedTreatment;
	}

	public void setBleedTreatment(String bleedTreatment) {
		this.bleedTreatment = bleedTreatment;
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

}
