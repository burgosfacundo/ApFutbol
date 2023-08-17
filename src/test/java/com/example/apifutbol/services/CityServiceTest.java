package com.example.apifutbol.services;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CityNotFoundException;
import com.example.apifutbol.model.City;
import com.example.apifutbol.model.dto.CityDTO;
import com.example.apifutbol.repository.CityRepository;
import com.example.apifutbol.service.CityService;
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
public class CityServiceTest {
    @Mock
    private CityRepository repository;

    @InjectMocks
    private CityService service;
    private final City city = new City();
    private CityDTO cityDTO;

    @BeforeEach
    void setUp(){
        cityDTO = new CityDTO(1L, "Buenos Aires",1L);
    }

    @Test
    @DisplayName("WHEN we create a city then don´t throws any exception")
    public void createCity(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertDoesNotThrow(()->service.create(cityDTO));
    }

    @Test
    @DisplayName("WHEN we create a city with the repeated name then it throws BadRequestException")
    public void createCityException(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.of(city));
        //WHEN AND THEN
        assertThrows(BadRequestException.class,()->service.create(cityDTO));
    }

    @Test
    @DisplayName("WHEN we list all the cities THEN don´t throws any exception")
    public void getAllCities(){
        //GIVEN
        given(repository.findAll()).willReturn(List.of(city));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getAll());
    }

    @Test
    @DisplayName("WHEN we list all the cities THEN return null")
    public void getAllCitiesNull(){
        //GIVEN
        given(repository.findAll()).willReturn(Collections.emptyList());
        //WHEN AND THEN
        assertNull(service.getAll());
    }

    @Test
    @DisplayName("WHEN we bring a city by id THEN don´t throws any exception")
    public void getByIdCity(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(city));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getById(1L));
    }
    @Test
    @DisplayName("WHEN we bring a city by id THEN it throws CityNotFoundException")
    public void getByIdCityException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(CityNotFoundException.class,()->service.getById(1L));
    }

    @Test
    @DisplayName("WHEN we bring a city by name THEN don´t throws any exception")
    public void getByNameCity(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.of(city));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getByName("Buenos Aires"));
    }
    @Test
    @DisplayName("WHEN we bring a city by name THEN it throws CityNotFoundException")
    public void getByNameCityException(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(CityNotFoundException.class,()->service.getByName("nombre"));
    }

    @Test
    @DisplayName("WHEN we update a city then don´t throws any exception")
    public void updateCity(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(city));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.update(cityDTO));
    }
    @Test
    @DisplayName("WHEN we update a city that not exists then it throws CityNotFoundException")
    public void updateCountryException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(CityNotFoundException.class,()->service.update(cityDTO));
    }

    @Test
    @DisplayName("WHEN we delete city THEN don´t throws any exception")
    public void deleteByIdCity(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(city));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.deleteById(1L));
    }
    @Test
    @DisplayName("WHEN we delete city that is not present in the db THEN it throws CityNotFoundException")
    public void deleteByIdCityException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(CityNotFoundException.class,()-> service.deleteById(5L));
    }
}
