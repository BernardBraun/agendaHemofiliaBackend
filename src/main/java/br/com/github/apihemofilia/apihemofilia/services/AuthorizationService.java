package br.com.github.apihemofilia.apihemofilia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.github.apihemofilia.apihemofilia.repositorys.LoginRepository;

@Service
public class AuthorizationService implements UserDetailsService {
	
	@Autowired
	LoginRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByLogin(username);
	}

}
