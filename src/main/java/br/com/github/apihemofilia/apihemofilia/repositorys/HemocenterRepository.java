package br.com.github.apihemofilia.apihemofilia.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Hemocenter;

public interface HemocenterRepository extends JpaRepository<Hemocenter, Integer>{

}
