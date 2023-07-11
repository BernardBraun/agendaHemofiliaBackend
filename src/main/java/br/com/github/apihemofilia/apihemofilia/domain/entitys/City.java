package br.com.github.apihemofilia.apihemofilia.domain.entitys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "city")
public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "state_id", nullable = false)
	private State state;

}
