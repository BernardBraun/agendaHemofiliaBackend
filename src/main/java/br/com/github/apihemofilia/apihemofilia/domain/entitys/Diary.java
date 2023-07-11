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

}
