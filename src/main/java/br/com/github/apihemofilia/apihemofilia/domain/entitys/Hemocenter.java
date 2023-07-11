package br.com.github.apihemofilia.apihemofilia.domain.entitys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hemocenter")
@Getter
@Setter
public class Hemocenter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name = "hemocenter_name", nullable = false)
	private String name;
	
	@Column(name = "hemocenter_phone", nullable = false, length = 11)
	private String phone;
	
	@OneToOne
	@JoinColumn(name = "city_id", nullable = false)
	private City city;

}
