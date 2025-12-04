package br.com.github.apihemofilia.apihemofilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.AuthRequest;
import br.com.github.apihemofilia.apihemofilia.domain.dtos.AuthResponse;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Login;
import br.com.github.apihemofilia.apihemofilia.infra.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Controller to authentic any user")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@Operation(summary = "Authentication", method = "POST")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User exits on application"),
			@ApiResponse(responseCode = "403", description = "User doesn't exists on application"),
			@ApiResponse(responseCode = "500", description = "Error internal...") })
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthResponse> login(@RequestBody final AuthRequest request) {
		try {
			var usernamePassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
			var auth = authManager.authenticate(usernamePassword);
			var token = tokenService.generatedToken((Login) auth.getPrincipal());
			return ResponseEntity.ok(new AuthResponse(token));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
