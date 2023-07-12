package br.com.github.apihemofilia.apihemofilia.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
