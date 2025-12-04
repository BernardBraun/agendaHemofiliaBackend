package br.com.github.apihemofilia.apihemofilia.services;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.DiaryDto;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Diary;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Hemocenter;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Person;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Treatment;
import br.com.github.apihemofilia.apihemofilia.functions.FactoryFunctions;
import br.com.github.apihemofilia.apihemofilia.repositorys.DiaryRepository;
import br.com.github.apihemofilia.apihemofilia.repositorys.HemocenterRepository;
import br.com.github.apihemofilia.apihemofilia.repositorys.PersonRepository;
import br.com.github.apihemofilia.apihemofilia.repositorys.TreatmentRepository;
import jakarta.transaction.Transactional;

@Service
public class DiaryService {

	final HemocenterRepository hemocenterRepository;
	final PersonRepository personRepository;
	final DiaryRepository diaryRepository;
	final TreatmentRepository treatmentRepository;

	public DiaryService(HemocenterRepository hemocenterRepository, PersonRepository personRepository,
			DiaryRepository diaryRepository, TreatmentRepository treatmentRepository) {
		this.hemocenterRepository = hemocenterRepository;
		this.personRepository = personRepository;
		this.diaryRepository = diaryRepository;
		this.treatmentRepository = treatmentRepository;
	}

	public Diary processData(final DiaryDto dto) {

		Assert.notNull(dto.personId(), "The person identifier cannot be null.");

		Function<Diary, Hemocenter> getHemocenter = FactoryFunctions.getHemocenterToDiary(dto.hemocenterId(),
				hemocenterRepository);
		Function<Diary, Person> getPerson = FactoryFunctions.getPersonToDiary(dto.personId(), personRepository);
		Function<Diary, Treatment> getTreatment = FactoryFunctions.getTreatmentToDiary(dto.treatment_id(), treatmentRepository);

		return new Diary(dto, getHemocenter, getPerson, getTreatment);
	}

	public Diary updateDiary(final Long diaryId, final DiaryDto updateDto) {

		Assert.notNull(updateDto.hemocenterId(), "The hemocenter identifier cannot be null.");

		Function<Diary, Hemocenter> getHemocenter = FactoryFunctions.getHemocenterToDiary(updateDto.hemocenterId(),
				hemocenterRepository);

		var diary = diaryRepository.findById(diaryId)
				.orElseThrow(() -> new IllegalArgumentException("Dosen't exists the register"));
		
		Function<Diary, Treatment> getTreatment = FactoryFunctions.getTreatmentToDiary(updateDto.treatment_id(), treatmentRepository);

		diary.updateDiary(updateDto, getHemocenter, getTreatment);

		return diary;
	}

	public List<Diary> getAllDiaryOfUser(final Long personId) {

		Assert.notNull(personId, "The person identifier cannot be null.");

		return diaryRepository.findByPersonId(personId);
	}

	@Transactional
	public void persistDiary(final Diary newDiary) {
		diaryRepository.save(newDiary);
	}

	@Transactional
	public void deleteDiary(final Long diaryId) {
		diaryRepository.deleteById(diaryId);
	}
}
