package com.example.apifutbol.controller;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CountryNotFoundException;
import com.example.apifutbol.model.dto.CountryRequestDTO;
import com.example.apifutbol.model.dto.CountryResponseDTO;
import com.example.apifutbol.service.CountryService;
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
public class CountryControllerTest {
    @Mock
    private CountryService service;
    @InjectMocks
    private CountryController controller;

    private final CountryResponseDTO countryResponse = new CountryResponseDTO(1L,"nombre",new HashSet<>());
    private final CountryRequestDTO countryRequest = new CountryRequestDTO(1L,"nombre");


    @Test
    @DisplayName("WHEN we create a country THEN return HTTP STATUS 201 CREATED and a message 'Se creo el pais correctamente'")
    public void createCountry() throws BadRequestException {
        //WHEN
        given(service.create(countryRequest)).willReturn(true);
        //THEN
        assertEquals(controller.create(countryRequest),new ResponseEntity<>("Se creo el pais correctamente", HttpStatus.CREATED));
    }

    @Test
    @DisplayName("WHEN we list all countries THEN return HTTP STATUS 200 OK and a list of countries")
    public void getAllCountries(){
        //WHEN
        given(service.getAll()).willReturn(List.of(countryResponse));
        //THEN
        assertEquals(controller.getAll(),ResponseEntity.ok(List.of(countryResponse)));
    }

    @Test
    @DisplayName("WHEN we bring a country by id THEN return HTTP STATUS 200 OK and a country")
    public void getCountryById() throws CountryNotFoundException {
        //WHEN
        given(service.getById(anyLong())).willReturn(countryResponse);
        //THEN
        assertEquals(controller.getById(anyLong()),ResponseEntity.ok(countryResponse));
    }

    @Test
    @DisplayName("WHEN we bring a country by name THEN return HTTP STATUS 200 OK and a country")
    public void getCountryByName() throws CountryNotFoundException {
        //WHEN
        given(service.getByName(anyString())).willReturn(countryResponse);
        //THEN
        assertEquals(controller.getByName(anyString()),ResponseEntity.ok(countryResponse));
    }

    @Test
    @DisplayName("WHEN we update a country THEN return HTTP STATUS 200 OK and a message 'Se edito el pais correctamente'")
    public void updateCountry() throws CountryNotFoundException, BadRequestException {
        //WHEN
        given(service.update(countryRequest)).willReturn(true);
        //THEN
        assertEquals(controller.update(countryRequest),new ResponseEntity<>("Se edito el pais correctamente", HttpStatus.OK));
    }

    @Test
    @DisplayName("WHEN we delete a country by id THEN return HTTP STATUS 200 OK and a message 'Se elimino el pais'")
    public void deleteCountryById() throws CountryNotFoundException {
        //WHEN
        given(service.deleteById(anyLong())).willReturn(true);
        //THEN
        assertEquals(controller.deleteById(anyLong()),new ResponseEntity<>("Se elimino el pais", HttpStatus.OK));
    }
}
