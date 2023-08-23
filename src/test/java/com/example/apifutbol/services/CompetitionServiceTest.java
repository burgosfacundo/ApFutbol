package com.example.apifutbol.services;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CompetitionNotFoundException;
import com.example.apifutbol.model.Competition;
import com.example.apifutbol.model.Country;
import com.example.apifutbol.model.dto.CompetitionRequestDTO;
import com.example.apifutbol.repository.CompetitionRepository;
import com.example.apifutbol.repository.CountryRepository;
import com.example.apifutbol.service.CompetitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CompetitionServiceTest {
    @Mock
    private CompetitionRepository repository;
    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CompetitionService service;
    private CompetitionRequestDTO competitionRequest;
    private final Competition competition = new Competition();
    private final Country country = new Country();

    @BeforeEach
    void setUp(){
        competitionRequest = new CompetitionRequestDTO(1L, "Premier League",1L);
    }

    @Test
    @DisplayName("WHEN we create a competition then don´t throws any exception")
    public void createCompetition(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.empty());
        given(countryRepository.findById(anyLong())).willReturn(Optional.of(country));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.create(competitionRequest));
    }

    @Test
    @DisplayName("WHEN we create a competition with the repeated name then it throws BadRequestException")
    public void createCompetitionException(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.of(competition));
        //WHEN AND THEN
        assertThrows(BadRequestException.class,()->service.create(competitionRequest));
    }

    @Test
    @DisplayName("WHEN we create a competition with the wrong country then it throws BadRequestException")
    public void createCompetitionException2(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.empty());
        given(countryRepository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(BadRequestException.class,()->service.create(competitionRequest));
    }

    @Test
    @DisplayName("WHEN we list all the competitions THEN don´t throws any exception")
    public void getAllCompetitions(){
        //GIVEN
        given(repository.findAll()).willReturn(List.of(competition));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getAll());
    }

    @Test
    @DisplayName("WHEN we list all the competitions THEN return null")
    public void getAllCompetitionsNull(){
        //GIVEN
        given(repository.findAll()).willReturn(Collections.emptyList());
        //WHEN AND THEN
        assertNull(service.getAll());
    }

    @Test
    @DisplayName("WHEN we bring a competition by id THEN don´t throws any exception")
    public void getByIdCompetition(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(competition));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getById(1L));
    }
    @Test
    @DisplayName("WHEN we bring a competition by id THEN it throws CompetitionNotFoundException")
    public void getByIdCompetitionException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(CompetitionNotFoundException.class,()->service.getById(1L));
    }

    @Test
    @DisplayName("WHEN we bring a competition by name THEN don´t throws any exception")
    public void getByNameCompetition(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.of(competition));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getByName("Premier league"));
    }
    @Test
    @DisplayName("WHEN we bring a country by name THEN it throws CompetitionNotFoundException")
    public void getByNameCompetitionException(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(CompetitionNotFoundException.class,()->service.getByName("La liga"));
    }
}
