package br.com.github.apihemofilia.apihemofilia.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("select p from Person p join fetch p.address join fetch p.login left join fetch p.diary where p.email = :email")
	Person getAllDataOfPerson(@Param("email") final String email);
	
	@Query("select p.completeName,"
			//+ " (select FUNCTION('FORMAT', max(d.infusionDate), 'dd/mm/yyyy') from Diary d where d.person = p) as infusionDate"
			+ " (select max(d.infusionDate) from Diary d where d.person = p) as infusionDate"
			+ " from Person p"
			+ " where p.email = :email")
	List<Object[]> getPersonWithMaxInfusionDate(@Param("email") final String email);

}
