package br.com.github.apihemofilia.apihemofilia.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.State;
import br.com.github.apihemofilia.apihemofilia.repositorys.StateRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/state")
@Tag(name = "Controller to data of states")
public class StateController {

	final StateRepository repository;

	public StateController(StateRepository repository) {
		this.repository = repository;
	}

	@Operation(summary = "Find all states", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All states") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<State>> getStates() {
		var states = repository.findAll();
		return ResponseEntity.ok(states);
	}

}
