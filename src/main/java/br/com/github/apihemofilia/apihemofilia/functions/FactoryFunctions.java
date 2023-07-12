package br.com.github.apihemofilia.apihemofilia.functions;

import java.util.function.Function;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Address;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.City;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Hemocenter;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Person;
import br.com.github.apihemofilia.apihemofilia.repositorys.CityRepository;
import br.com.github.apihemofilia.apihemofilia.repositorys.HemocenterRepository;

public final class FactoryFunctions {

	/* Function to return a register of hemocenter from Id */
	public static Function<Person, Hemocenter> getHemocenterToPerson(final Integer hemocenterId,
			HemocenterRepository hemocenterRepository) {
		return person -> {
			return hemocenterRepository.findById(hemocenterId).get();
		};
	}

	/* Function to return a register of city from Id */
	public static Function<Address, City> getCityToAddress(final Integer cityId, CityRepository cityRepository) {
		return address -> {
			return cityRepository.findById(cityId).get();
		};
	}
}
