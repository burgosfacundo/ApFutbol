package com.example.apifutbol.controller;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CityNotFoundException;
import com.example.apifutbol.model.dto.CityDTO;
import com.example.apifutbol.service.CityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CityControllerTest {
    @Mock
    private CityService service;
    @InjectMocks
    private CityController controller;
    private final CityDTO cityDTO = new CityDTO(1L,"nombre",1L);

    @Test
    @DisplayName("WHEN we create a city THEN return HTTP STATUS 201 CREATED and a message 'Se creo la ciudad correctamente'")
    public void createCity() throws BadRequestException {
        //WHEN
        given(service.create(cityDTO)).willReturn(true);
        //THEN
        assertEquals(controller.create(cityDTO),new ResponseEntity<>("Se creo la ciudad correctamente", HttpStatus.CREATED));
    }

    @Test
    @DisplayName("WHEN we list all cities THEN return HTTP STATUS 200 OK and a list of cities")
    public void getAllCities(){
        //WHEN
        given(service.getAll()).willReturn(List.of(cityDTO));
        //THEN
        assertEquals(controller.getAll(),ResponseEntity.ok(List.of(cityDTO)));
    }

    @Test
    @DisplayName("WHEN we bring a city by id THEN return HTTP STATUS 200 OK and a city")
    public void getCityById() throws CityNotFoundException {
        //WHEN
        given(service.getById(anyLong())).willReturn(cityDTO);
        //THEN
        assertEquals(controller.getById(anyLong()),ResponseEntity.ok(cityDTO));
    }

    @Test
    @DisplayName("WHEN we bring a city by name THEN return HTTP STATUS 200 OK and a city")
    public void getCityByName() throws CityNotFoundException {
        //WHEN
        given(service.getByName(anyString())).willReturn(cityDTO);
        //THEN
        assertEquals(controller.getByName(anyString()),ResponseEntity.ok(cityDTO));
    }

    @Test
    @DisplayName("WHEN we update a city THEN return HTTP STATUS 200 OK and a message 'Se edito la ciudad correctamente'")
    public void updateCity() throws CityNotFoundException, BadRequestException {
        //WHEN
        given(service.update(cityDTO)).willReturn(true);
        //THEN
        assertEquals(controller.update(cityDTO),new ResponseEntity<>("Se edito la ciudad correctamente", HttpStatus.OK));
    }

    @Test
    @DisplayName("WHEN we delete a city by id THEN return HTTP STATUS 200 OK and a message 'Se elimino la ciudad'")
    public void deleteCityById() throws CityNotFoundException {
        //WHEN
        given(service.deleteById(anyLong())).willReturn(true);
        //THEN
        assertEquals(controller.deleteById(anyLong()),new ResponseEntity<>("Se elimino la ciudad", HttpStatus.OK));
    }
}
