package br.com.github.apihemofilia.apihemofilia.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.DiaryDto;
import br.com.github.apihemofilia.apihemofilia.domain.dtos.GenericResponse;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Diary;
import br.com.github.apihemofilia.apihemofilia.log.FactoryLog;
import br.com.github.apihemofilia.apihemofilia.services.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/api/diary")
@Tag(name = "Controller to data of diary")
public class DiaryController {

	private final Logger LOGGER = FactoryLog.getLog();

	final DiaryService service;

	public DiaryController(DiaryService service) {
		this.service = service;
	}

	@Secured("ROLE_USER")
	@Operation(summary = "Creation of new register of diary", method = "POST")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created register with success"),
			@ApiResponse(responseCode = "500", description = "Error internal...") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> createARegisaterOnDiary(@RequestBody final DiaryDto dto) {
		try {

			final Diary newLineDiary = service.processData(dto);
			service.persistDiary(newLineDiary);

			var genericResponse = new GenericResponse("Created user with success", HttpStatus.CREATED.value(),
					"There were no mistakes");

			LOGGER.info("Sucesso na criação de um novo registro no diario.");

			return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);

		} catch (Exception e) {

			var genericResponse = new GenericResponse("Error internal...", HttpStatus.INTERNAL_SERVER_ERROR.value(),
					e.getLocalizedMessage());

			LOGGER.info(String.format("Erro na criação de um novo registro no diario. Erro: [%s] ",
					e.getLocalizedMessage()));

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
		}
	}

	@Operation(summary = "Update on a register of diary", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update in the register of the agenda was done successfully"),
			@ApiResponse(responseCode = "500", description = "Error internal...") })
	@PutMapping(value = "/{diaryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> updateRegisterOfDiary(@PathVariable("diaryId") final Long diaryId,
			@RequestBody DiaryDto updateDto) {
		try {

			var updateDiary = service.updateDiary(diaryId, updateDto);
			service.persistDiary(updateDiary);

			var genericResponse = new GenericResponse("Update in the register of the agenda was done successfully",
					HttpStatus.OK.value(), "There were no mistakes");

			LOGGER.info("Sucesso na atualização de um registro no diario.");

			return ResponseEntity.ok(genericResponse);

		} catch (Exception e) {

			var genericResponse = new GenericResponse("Error internal...", HttpStatus.INTERNAL_SERVER_ERROR.value(),
					e.getLocalizedMessage());

			LOGGER.info(
					String.format("Erro na atualização no registro no diario. Erro: [%s] ", e.getLocalizedMessage()));

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
		}
	}

	@Operation(summary = "Find all register on diary of user", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Get all registers of user"),
			@ApiResponse(responseCode = "500", description = "Error internal...") })
	@GetMapping(value = "/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDiaryOfUser(@PathVariable("personId") final Long personId) {
		try {
			List<Diary> allDiaryOfUser = service.getAllDiaryOfUser(personId);
			return ResponseEntity.ok(allDiaryOfUser);
		} catch (Exception e) {

			var genericResponse = new GenericResponse("Error internal...", HttpStatus.INTERNAL_SERVER_ERROR.value(),
					e.getLocalizedMessage());

			LOGGER.info(String.format("Erro na busca dos registros no diario. Erro: [%s] ", e.getLocalizedMessage()));

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
		}
	}

	@Operation(summary = "Remove a register on diary of user", method = "GET")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Register removed was done successfully"),
			@ApiResponse(responseCode = "500", description = "Error internal...") })
	@DeleteMapping(value = "/{diaryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponse> deleteLineOfDiary(@PathVariable("diaryId") final Long diaryId) {
		try {

			service.deleteDiary(diaryId);

			var genericResponse = new GenericResponse("Record has been successfully deleted", HttpStatus.OK.value(),
					"There were no mistakes");

			return ResponseEntity.ok(genericResponse);

		} catch (Exception e) {

			var genericResponse = new GenericResponse("Error internal...", HttpStatus.INTERNAL_SERVER_ERROR.value(),
					e.getLocalizedMessage());

			LOGGER.info(String.format("Erro ao apagar o registro no diario. Erro: [%s] ", e.getLocalizedMessage()));

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
		}

	}
}
