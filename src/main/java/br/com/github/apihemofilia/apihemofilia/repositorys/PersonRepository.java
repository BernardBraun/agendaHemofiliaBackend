package br.com.github.apihemofilia.apihemofilia.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("select p from Person p join fetch p.address join fetch p.login left join fetch p.diary where p.email = :email")
	Person getAllDataOfPerson(@Param("email") final String email);

}
