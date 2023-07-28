package br.com.github.apihemofilia.apihemofilia.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.apihemofilia.apihemofilia.domain.entitys.Treatment;
import br.com.github.apihemofilia.apihemofilia.repositorys.TreatmentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/treatment")
@Tag(name = "Controller to data of treatment")
public class TreatmentController {

	final TreatmentRepository reposiroty;

	public TreatmentController(TreatmentRepository reposiroty) {
		this.reposiroty = reposiroty;
	}

	@Operation(summary = "Data of treatment", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "All data type avaliable") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Treatment>> getAllTreatment() {
		var treatments = reposiroty.findAll();
		return ResponseEntity.ok(treatments);
	}

}
