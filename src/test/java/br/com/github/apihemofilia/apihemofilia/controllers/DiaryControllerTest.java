package br.com.github.apihemofilia.apihemofilia.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.github.apihemofilia.apihemofilia.domain.dtos.DiaryDto;
import br.com.github.apihemofilia.apihemofilia.domain.dtos.GenericResponse;
import br.com.github.apihemofilia.apihemofilia.domain.entitys.Diary;
import br.com.github.apihemofilia.apihemofilia.services.DiaryService;

@MockitoSettings
@ExtendWith(MockitoExtension.class)
public class DiaryControllerTest {
	
	@Mock
    private DiaryService service;
    
    @InjectMocks
    private DiaryController controller;
    
    private DiaryDto dto, updateDto;
    private Diary newLineDiary, updateDiary;
    
    @BeforeEach
    void setup() {
    	dto = new DiaryDto(null, null, null, null, null, null, null);
    	updateDto = new DiaryDto(null, null, null, null, null, null, null);
    	newLineDiary = new Diary();
    	updateDiary = new Diary();
    }
	
    @Test
    public void createARegisaterOnDiary_SuccessTest() {
        when(service.processData(any(DiaryDto.class))).thenReturn(newLineDiary);
        ResponseEntity<GenericResponse> response = controller.createARegisaterOnDiary(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Created user with success", response.getBody().message());
        assertEquals(HttpStatus.CREATED.value(), response.getBody().code());
        assertEquals("There were no mistakes", response.getBody().error());
    }
    
    @Test
    public void createARegisaterOnDiary_FailureTest() {
        when(service.processData(any(DiaryDto.class))).thenThrow(new RuntimeException("Simulated error"));
        ResponseEntity<GenericResponse> response = controller.createARegisaterOnDiary(dto);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error internal...", response.getBody().message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().code());
        assertEquals("Simulated error", response.getBody().error());
    }
    
    @Test
    public void updateRegisterOfDiary_SuccessTest() {
        Long diaryId = 1L;
        when(service.updateDiary(diaryId, updateDto)).thenReturn(updateDiary);
        ResponseEntity<GenericResponse> response = controller.updateRegisterOfDiary(diaryId, updateDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Update in the register of the agenda was done successfully", response.getBody().message());
        assertEquals(HttpStatus.OK.value(), response.getBody().code());
        assertEquals("There were no mistakes", response.getBody().error());
    }
    
    @Test
    public void updateRegisterOfDiary_FailureTest() {
        Long diaryId = 1L;
        when(service.updateDiary(diaryId, updateDto)).thenThrow(new RuntimeException("Simulated error"));
        ResponseEntity<GenericResponse> response = controller.updateRegisterOfDiary(diaryId, updateDto);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error internal...", response.getBody().message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().code());
        assertEquals("Simulated error", response.getBody().error());

    }
    
    @Test
    public void getDiaryOfUser_SuccessTest() {
        Long personId = 1L;
        List<Diary> allDiaryOfUser = new ArrayList<>();
        when(service.getAllDiaryOfUser(personId)).thenReturn(allDiaryOfUser);
        ResponseEntity<?> response = controller.getDiaryOfUser(personId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allDiaryOfUser, response.getBody());
    }
    
    @Test
    public void getDiaryOfUser_FailureTest() {
        Long personId = 1L;
        when(service.getAllDiaryOfUser(personId)).thenThrow(new RuntimeException("Simulated error"));
        ResponseEntity<GenericResponse> response = (ResponseEntity<GenericResponse>) controller.getDiaryOfUser(personId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error internal...", response.getBody().message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().code());
        assertEquals("Simulated error", response.getBody().error());
    }
    
    @Test
    public void getDiaryOfUser_FailureTest_2() {
        when(service.getAllDiaryOfUser(null)).thenThrow(new IllegalArgumentException("The person identifier cannot be null."));
        ResponseEntity<GenericResponse> response = (ResponseEntity<GenericResponse>) controller.getDiaryOfUser(null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error internal...", response.getBody().message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().code());
        assertEquals("The person identifier cannot be null.", response.getBody().error());
    }
    
    @Test
    public void deleteLineOfDiary_SuccessTest() {
        Long diaryId = 1L;
        doNothing().when(service).deleteDiary(diaryId);
        ResponseEntity<GenericResponse> response = controller.deleteLineOfDiary(diaryId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Record has been successfully deleted", response.getBody().message());
        assertEquals(HttpStatus.OK.value(), response.getBody().code());
        assertEquals("There were no mistakes", response.getBody().error());
        verify(service).deleteDiary(diaryId);
    }
    
    @Test
    public void deleteLineOfDiary_FailureTest() {
        Long diaryId = 1L;
        doThrow(new RuntimeException("Simulated error")).when(service).deleteDiary(diaryId);
        ResponseEntity<GenericResponse> response = controller.deleteLineOfDiary(diaryId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error internal...", response.getBody().message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().code());
        assertEquals("Simulated error", response.getBody().error());
        verify(service).deleteDiary(diaryId);
    }

}
