package br.com.github.apihemofilia.apihemofilia.domain.entitys;

import java.io.Serializable;
import java.util.function.Function;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.AddressDto;
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
	
	public Address() {
		
	}
	
	public Address(final AddressDto dto, Function<Address, City> getCity) {
		this.streetName = dto.streetName();
		this.district = dto.district();
		this.postalCode = dto.postalCode();
		//receber funcão para encontrar a cidade
		this.city = getCity.apply(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
