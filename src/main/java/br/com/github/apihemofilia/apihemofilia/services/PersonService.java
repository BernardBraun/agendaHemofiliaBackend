package br.com.github.apihemofilia.apihemofilia.services;

import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.PersonDto;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Address;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.City;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Hemocenter;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Person;
import br.com.github.apihemofilia.apihemofilia.repositorys.CityRepository;
import br.com.github.apihemofilia.apihemofilia.repositorys.HemocenterRepository;
import br.com.github.apihemofilia.apihemofilia.repositorys.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService {

	final HemocenterRepository hemocenterRepository;
	final CityRepository cityRepository;
	final PersonRepository personRepository;

	public PersonService(HemocenterRepository hemocenterRepository, CityRepository cityRepository, PersonRepository personRepository) {
		this.hemocenterRepository = hemocenterRepository;
		this.cityRepository = cityRepository;
		this.personRepository = personRepository;
	}

	public Person processDataPerson(final PersonDto dto) {
		
		Assert.isNull(dto.hemocenterId(), "The blood center identifier cannot be null.");
		Assert.isNull(dto.address().cityId(), "The city identifier cannot be null.");
		
		Function<Person, Hemocenter> getHemocenter = getHemocenterToPerson(dto.hemocenterId(), hemocenterRepository);
		Function<Address, City> getCity = getCityToAddress(dto.address().cityId(), cityRepository);
		
		return new Person(dto, getHemocenter, getCity);
	}

	/* Function to return a register of hemocenter from Id */
	private Function<Person, Hemocenter> getHemocenterToPerson(final Integer hemocenterId,
			HemocenterRepository hemocenterRepository) {
		return person -> {
			return hemocenterRepository.findById(hemocenterId).get();
		};
	}

	/* Function to return a register of city from Id */
	private Function<Address, City> getCityToAddress(final Integer cityId, CityRepository cityRepository) {
		return address -> {
			return cityRepository.findById(cityId).get();
		};
	}

	@Transactional
	public void persist(Person newPerson) {
		personRepository.save(newPerson);
	}
}
