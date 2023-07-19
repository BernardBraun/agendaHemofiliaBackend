package br.com.github.apihemofilia.apihemofilia.services;

import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.BleedInformationDto;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.BleedInform;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Person;
import br.com.github.apihemofilia.apihemofilia.functions.FactoryFunctions;
import br.com.github.apihemofilia.apihemofilia.repositorys.BleedInformationRepository;
import br.com.github.apihemofilia.apihemofilia.repositorys.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class BleedService {

    final PersonRepository personRepository;
    final BleedInformationRepository bleedInformationRepository;

    public BleedService(PersonRepository personRepository, BleedInformationRepository bleedInformationRepository) {
		this.personRepository = personRepository;
		this.bleedInformationRepository = bleedInformationRepository;
	}

	public BleedInform process(final BleedInformationDto dto) {

        Assert.notNull(dto.personId(), "The person identifier cannot be null.");

        Function<BleedInform, Person> getPerson = FactoryFunctions.getPersonToBleedInformation(dto.personId(), personRepository);

        return new BleedInform(dto, getPerson);
    }

    @Transactional
    public void persistBleedinformation(final BleedInform newBleedInform) {
        bleedInformationRepository.save(newBleedInform);
    }
}