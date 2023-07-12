package br.com.github.apihemofilia.apihemofilia.domain.entitys;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.PersonDto;
import br.com.github.apihemofilia.apihemofilia.enums.HemofiliaType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "complete_name", nullable = false, length = 40)
	private String completeName;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	@Column(name = "height", nullable = false)
	private Float height;

	@Column(name = "wieght")
	private Float wieght;

	@Column(name = "hemofilia_type", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private HemofiliaType hemofiliaType;

	@Column(name = "infusion_days", nullable = false)
	private Integer infusionDays;

	@Column(name = "cell_phone", nullable = false, length = 11)
	private String cellPhone;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "father_name", nullable = false, length = 40)
	private String fatherName;

	@Column(name = "mother_name", nullable = false, length = 40)
	private String motherName;

	@Column(name = "inhibitor", nullable = false)
	private boolean inhibitor;

	@OneToOne
	@JoinColumn(name = "hemocenter_id", nullable = false)
	private Hemocenter hemocenter;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "login_id", nullable = false)
	private Login login;

	@OneToMany(mappedBy = "person")
	private List<Diary> diary;

	public Person() {

	}

	public Person(final PersonDto dto, Function<Person, Hemocenter> getHemocenter, Function<Address, City> getCity) {
		this.completeName = dto.completeName();
		this.birthDate = dto.birthDate();
		this.height = dto.height();
		this.wieght = dto.wieght();
		this.hemofiliaType = dto.hemofiliaType();
		this.infusionDays = dto.infusionDays();
		this.cellPhone = dto.cellPhone();
		this.email = dto.email();
		this.fatherName = dto.fatherName();
		this.motherName = dto.motherName();
		this.inhibitor = dto.inhibitor();
		this.hemocenter = getHemocenter.apply(this);
		this.address = new Address(dto.address(), getCity);
		this.login = new Login(dto.login());
	}

	@Override
	public String toString() {
		return "Person [completeName=" + completeName + ", email=" + email + "]";
	}
	
}
