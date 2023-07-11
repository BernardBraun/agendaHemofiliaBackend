package br.com.github.apihemofilia.apihemofilia.domain.entitys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "street_name", nullable = false, length = 200)
	private String streetName;
	
	@Column(name = "district", nullable = false, length = 80)
	private String district;
	
	@Column(name = "postal_code", nullable = false, length = 9)
	private String postalCode;
	
	@OneToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;

}
