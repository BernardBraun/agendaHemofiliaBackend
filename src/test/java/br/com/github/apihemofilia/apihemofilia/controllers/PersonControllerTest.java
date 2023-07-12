package br.com.github.apihemofilia.apihemofilia.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.GenericResponse;
import br.com.github.apihemofilia.apihemofilia.domain.dtos.PersonDto;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Person;
import br.com.github.apihemofilia.apihemofilia.services.PersonService;

@MockitoSettings
@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
	
	@Mock
    private PersonService service;
    
    @InjectMocks
    private PersonController controller;
    
    @Test
    void createPerson_SuccessfulCreation_ReturnsCreatedResponse() {
        // Arrange
        PersonDto personDto = new PersonDto(null, null, null, null, null, null, null, null, null, null, false, null, null, null);

        // Set up any necessary properties for personDto
        
        Person createdPerson = new Person();
        // Set up any necessary properties for createdPerson
        
        when(service.processDataPerson(personDto)).thenReturn(createdPerson);
        
        GenericResponse expectedResponse = new GenericResponse("Created user with success", HttpStatus.CREATED.value(), "There were no mistakes");
        
        // Act
        ResponseEntity<GenericResponse> response = controller.createPerson(personDto);
        
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        
        // Verify that the service methods were called
        verify(service, times(1)).processDataPerson(personDto);
        verify(service, times(1)).persist(createdPerson);
    }
    
    @Test
    void createPerson_ExceptionThrown_ReturnsInternalServerErrorResponse() {
        // Arrange
    	PersonDto personDto = new PersonDto(null, null, null, null, null, null, null, null, null, null, false, null, null, null);
        
        RuntimeException exception = new RuntimeException("Error internal...");
        
        when(service.processDataPerson(personDto)).thenThrow(exception);
        
        GenericResponse expectedResponse = new GenericResponse("Error internal...", HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage());
        
        // Act
        ResponseEntity<GenericResponse> response = controller.createPerson(personDto);
        
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        
        // Verify that the service methods were called
        verify(service, times(1)).processDataPerson(personDto);
        verifyNoMoreInteractions(service); // Ensure that no other methods were called
    }
}
