package br.com.github.apihemofilia.apihemofilia.domain.entitys;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bleed_inform")
@Getter
@Setter
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

}
