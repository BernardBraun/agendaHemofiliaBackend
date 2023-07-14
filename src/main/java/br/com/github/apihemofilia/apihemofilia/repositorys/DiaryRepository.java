package br.com.github.apihemofilia.apihemofilia.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

	List<Diary> findByPersonId(final Long personId);
}
