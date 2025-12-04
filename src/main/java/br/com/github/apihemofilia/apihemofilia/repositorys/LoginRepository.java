package br.com.github.apihemofilia.apihemofilia.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{

	UserDetails findByLogin(final String login);
}
