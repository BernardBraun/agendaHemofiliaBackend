package br.com.github.apihemofilia.apihemofilia.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.GenericResponse;
import br.com.github.apihemofilia.apihemofilia.domain.dtos.PersonDto;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Person;
import br.com.github.apihemofilia.apihemofilia.log.FactoryLog;
import br.com.github.apihemofilia.apihemofilia.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person")
@Tag(name = "Controller to data of person")
public class PersonController {

	private final Logger LOGGER = FactoryLog.getLog();

	@Autowired
	PersonService service;

	@Operation(summary = "Creation of new person", method = "POST")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created user with success"),
			@ApiResponse(responseCode = "500", description = "Error internal...") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> createPerson(@RequestBody PersonDto personRequest) {
		try {
			LOGGER.info("Criação de uma novo registro de usuário.");

			Person newPerson = service.processDataPerson(personRequest);

			LOGGER.info(String.format("Registro montado com sucesso, info[%s]", newPerson));
			service.persist(newPerson);

			var genericResponse = new GenericResponse("Created user with success", HttpStatus.CREATED.value(),
					"There were no mistakes");
			LOGGER.info("Sucesso na criação de um novo registro de usuário.");
			return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
		} catch (Exception e) {
			var genericResponse = new GenericResponse("Error internal...", HttpStatus.INTERNAL_SERVER_ERROR.value(),
					e.getLocalizedMessage());
			LOGGER.info(String.format("Erro na criação de um novo registro de pessoa. Erro: [%s] ",
					e.getLocalizedMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
		}
	}

	@Operation(summary = "Find all data about person", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success on search"),
			@ApiResponse(responseCode = "403", description = "Error on validation of token") })
	@GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getDataOfUser(@PathVariable("email") final String email) {
		return ResponseEntity.ok(service.getCompletePerson(email));
	}

}
