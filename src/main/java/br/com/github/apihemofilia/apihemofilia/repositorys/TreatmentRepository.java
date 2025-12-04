package br.com.github.apihemofilia.apihemofilia.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

}
