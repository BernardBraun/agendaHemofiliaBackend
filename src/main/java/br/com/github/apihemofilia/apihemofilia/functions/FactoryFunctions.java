package br.com.github.apihemofilia.apihemofilia.functions;

import java.util.Objects;
import java.util.function.Function;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Address;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.BleedInform;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.City;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Diary;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Hemocenter;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Person;
import br.com.github.apihemofilia.apihemofilia.repositorys.CityRepository;
import br.com.github.apihemofilia.apihemofilia.repositorys.HemocenterRepository;
import br.com.github.apihemofilia.apihemofilia.repositorys.PersonRepository;

public final class FactoryFunctions {

	/* Function to build a hemocenter to person */
	public static Function<Person, Hemocenter> getHemocenterToPerson(final Integer hemocenterId,
			HemocenterRepository hemocenterRepository) {
		return person -> {
			return hemocenterRepository.findById(hemocenterId).get();
		};
	}

	/* Function to build a city to address */
	public static Function<Address, City> getCityToAddress(final Integer cityId, CityRepository cityRepository) {
		return address -> {
			return cityRepository.findById(cityId).get();
		};
	}

	/* Function to build person to diary */
	public static Function<Diary, Person> getPersonToDiary(final Long personId, PersonRepository personRepository) {
		return diary -> {
			return personRepository.findById(personId).get();
		};
	}

	/* Function to build hemocenter, if needs, to diary */
	public static Function<Diary, Hemocenter> getHemocenterToDiary(final Integer hemocenterId,
			HemocenterRepository hemocenterRepository) {
		return diary -> {
			if (Objects.isNull(hemocenterId)) {
				return null;
			} else {
				return hemocenterRepository.findById(hemocenterId).get();
			}
		};
	}

	/* Function to build a Person to Bleed information */
	public static Function<BleedInform, Person> getPersonToBleedinformation(final Long personId,
			PersonRepository personRepository) {
		return bleed -> {
			return personRepository.findById(personId).get();
		};
	}
}
