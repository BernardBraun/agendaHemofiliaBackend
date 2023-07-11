package br.com.github.apihemofilia.apihemofilia.domain.entitys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "state")
@Getter
@Setter
public class State implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "uf", nullable = false, length = 2)
	private String uf;

}
