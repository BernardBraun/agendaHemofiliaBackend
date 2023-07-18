package br.com.github.apihemofilia.apihemofilia.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.BleedInformationDto;
import br.com.github.apihemofilia.apihemofilia.domain.dtos.GenericResponse;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.BleedInform;
import br.com.github.apihemofilia.apihemofilia.log.FactoryLog;
import br.com.github.apihemofilia.apihemofilia.services.BleedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/bleeding")
@Tag(name = "Controller to data of bleed information")
public class BleedInformatioinController {

    private final Logger LOGGER = FactoryLog.getLog();

    @Autowired
    BleedService service;

    @Operation(summary = "Creation of new register of bleed information", method = "POST")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created register with success"),
            @ApiResponse(responseCode = "500", description = "Error internal...") })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> newBleeding(@RequestBody BleedInformationDto dto) {
        try {
            final BleedInform newBleedInformation = service.process(dto);
            service.persistBleedinformation(newBleedInformation);

            var genericResponse = new GenericResponse("Created user with success", HttpStatus.CREATED.value(),
                    "There were no mistakes");

            LOGGER.info("Sucesso na criação de um novo registro no diario.");

            return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
        } catch (Exception e) {
            var genericResponse = new GenericResponse("Error internal...", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getLocalizedMessage());

            LOGGER.info(
                    String.format("Erro na atualização no registro no diario. Erro: [%s] ", e.getLocalizedMessage()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genericResponse);
        }
    }

}
