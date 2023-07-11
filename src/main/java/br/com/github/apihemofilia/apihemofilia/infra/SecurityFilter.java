package br.com.github.apihemofilia.apihemofilia.infra;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.github.apihemofilia.apihemofilia.repositorys.LoginRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	TokenService tokenService;

	@Autowired
	LoginRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var token = recoveryToken(request);
		if (Objects.nonNull(token)) {
			var login = tokenService.validateToken(token);
			UserDetails user = repository.findByLogin(login);

			var authenticator = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authenticator);
		}
		filterChain.doFilter(request, response);
	}

	private String recoveryToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if (Objects.isNull(authHeader)) {
			return null;
		}
		return authHeader.replace("Bearer ", "");
	}

}
