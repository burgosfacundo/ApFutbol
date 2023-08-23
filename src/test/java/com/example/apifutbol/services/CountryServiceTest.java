package com.example.apifutbol.services;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CityNotFoundException;
import com.example.apifutbol.exception.CountryNotFoundException;
import com.example.apifutbol.model.Country;
import com.example.apifutbol.model.dto.CountryRequestDTO;
import com.example.apifutbol.repository.CountryRepository;
import com.example.apifutbol.service.CountryService;
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
public class CountryServiceTest {
    @Mock
    private CountryRepository repository;

    @InjectMocks
    private CountryService service;
    private CountryRequestDTO countryRequest;
    private final Country country = new Country();

    @BeforeEach
    void setUp(){
        countryRequest = new CountryRequestDTO(1L, "Argentina");
    }

    @Test
    @DisplayName("WHEN we create a country then don´t throws any exception")
    public void createCountry(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertDoesNotThrow(()->service.create(countryRequest));
    }

    @Test
    @DisplayName("WHEN we create a country with the repeated name then it throws BadRequestException")
    public void createCountryException(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.of(country));
        //WHEN AND THEN
        assertThrows(BadRequestException.class,()->service.create(countryRequest));
    }

    @Test
    @DisplayName("WHEN we list all the countries THEN don´t throws any exception")
    public void getAllCountries(){
        //GIVEN
        given(repository.findAll()).willReturn(List.of(country));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getAll());
    }

    @Test
    @DisplayName("WHEN we list all the countries THEN return null")
    public void getAllCountriesNull(){
        //GIVEN
        given(repository.findAll()).willReturn(Collections.emptyList());
        //WHEN AND THEN
        assertNull(service.getAll());
    }

    @Test
    @DisplayName("WHEN we bring a country by id THEN don´t throws any exception")
    public void getByIdCountry(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(country));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getById(1L));
    }
    @Test
    @DisplayName("WHEN we bring a country by id THEN it throws CountryNotFoundException")
    public void getByIdCountryException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(CountryNotFoundException.class,()->service.getById(1L));
    }

    @Test
    @DisplayName("WHEN we bring a country by name THEN don´t throws any exception")
    public void getByNameCountry(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.of(country));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.getByName("Argentina"));
    }
    @Test
    @DisplayName("WHEN we bring a country by name THEN it throws CountryNotFoundException")
    public void getByNameCountryException(){
        //GIVEN
        given(repository.findByName(anyString())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(CountryNotFoundException.class,()->service.getByName("Brasil"));
    }

    @Test
    @DisplayName("WHEN we update a country then don´t throws any exception")
    public void updateCountry(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(country));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.update(countryRequest));
    }
    @Test
    @DisplayName("WHEN we update a country that not exists then it throws CountryNotFoundException")
    public void updateCountryException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(CountryNotFoundException.class,()->service.update(countryRequest));
    }

    @Test
    @DisplayName("WHEN we delete country THEN don´t throws any exception")
    public void deleteByIdCountry(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.of(country));
        //WHEN AND THEN
        assertDoesNotThrow(()->service.deleteById(1L));
    }
    @Test
    @DisplayName("WHEN we delete country that is not present in the db THEN it throws CountryNotFoundException")
    public void deleteByIdCountryException(){
        //GIVEN
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        //WHEN AND THEN
        assertThrows(CountryNotFoundException.class,()-> service.deleteById(5L));
    }
}
