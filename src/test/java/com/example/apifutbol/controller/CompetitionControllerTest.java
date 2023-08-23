package com.example.apifutbol.controller;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CompetitionNotFoundException;
import com.example.apifutbol.model.Country;
import com.example.apifutbol.model.dto.CompetitionRequestDTO;
import com.example.apifutbol.model.dto.CompetitionResponseDTO;
import com.example.apifutbol.service.CompetitionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CompetitionControllerTest {
    @Mock
    private CompetitionService service;
    @InjectMocks
    private CompetitionController controller;

    private final CompetitionResponseDTO competitionResponseDTO = new CompetitionResponseDTO(1L,"nombre",new Country(),new HashSet<>());
    private final CompetitionRequestDTO competitionRequestDTO = new CompetitionRequestDTO(1L,"nombre",1L);


    @Test
    @DisplayName("WHEN we create a competition THEN return HTTP STATUS 201 CREATED and a message 'Se creo la competencia correctamente'")
    public void createCompetition() throws BadRequestException {
        //WHEN
        given(service.create(competitionRequestDTO)).willReturn(true);
        //THEN
        assertEquals(controller.create(competitionRequestDTO),new ResponseEntity<>("Se creo la competencia correctamente", HttpStatus.CREATED));
    }

    @Test
    @DisplayName("WHEN we list all competitions THEN return HTTP STATUS 200 OK and a list of competitions")
    public void getAllCompetitions(){
        //WHEN
        given(service.getAll()).willReturn(List.of(competitionResponseDTO));
        //THEN
        assertEquals(controller.getAll(),ResponseEntity.ok(List.of(competitionResponseDTO)));
    }

    @Test
    @DisplayName("WHEN we bring a competition by id THEN return HTTP STATUS 200 OK and a competition")
    public void getCompetitionById() throws CompetitionNotFoundException {
        //WHEN
        given(service.getById(anyLong())).willReturn(competitionResponseDTO);
        //THEN
        assertEquals(controller.getById(anyLong()),ResponseEntity.ok(competitionResponseDTO));
    }

    @Test
    @DisplayName("WHEN we bring a competition by name THEN return HTTP STATUS 200 OK and a competition")
    public void getCompetitionByName() throws CompetitionNotFoundException {
        //WHEN
        given(service.getByName(anyString())).willReturn(competitionResponseDTO);
        //THEN
        assertEquals(controller.getByName(anyString()),ResponseEntity.ok(competitionResponseDTO));
    }

    @Test
    @DisplayName("WHEN we update a competition THEN return HTTP STATUS 200 OK and a message 'Se edito la competencia correctamente'")
    public void updateCompetition() throws BadRequestException, CompetitionNotFoundException {
        //WHEN
        given(service.update(competitionRequestDTO)).willReturn(true);
        //THEN
        assertEquals(controller.update(competitionRequestDTO),new ResponseEntity<>("Se edito la competencia correctamente", HttpStatus.OK));
    }

    @Test
    @DisplayName("WHEN we delete a competition by id THEN return HTTP STATUS 200 OK and a message 'Se elimino la competencia'")
    public void deleteCompetitionById() throws CompetitionNotFoundException {
        //WHEN
        given(service.deleteById(anyLong())).willReturn(true);
        //THEN
        assertEquals(controller.deleteById(anyLong()),new ResponseEntity<>("Se elimino la competencia", HttpStatus.OK));
    }
}
