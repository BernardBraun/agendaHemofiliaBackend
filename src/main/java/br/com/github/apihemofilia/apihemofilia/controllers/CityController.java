package br.com.github.apihemofilia.apihemofilia.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.City;
import br.com.github.apihemofilia.apihemofilia.repositorys.CityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cities")
@Tag(name = "Controller to data of cities")
public class CityController {
	
	final CityRepository repository;

	public CityController(CityRepository repository) {
		this.repository = repository;
	}
	
	@Operation(summary = "Find all cities per state", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All cities of state") })
	@GetMapping(value = "/{stateId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getAllCitiesOfState(@PathVariable("stateId") final Integer stateId) {
		var cities = repository.findByStateId(stateId);
		return ResponseEntity.ok(cities);
	}

}
