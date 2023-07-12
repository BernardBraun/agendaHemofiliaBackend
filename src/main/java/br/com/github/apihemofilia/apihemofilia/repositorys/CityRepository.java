package br.com.github.apihemofilia.apihemofilia.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
