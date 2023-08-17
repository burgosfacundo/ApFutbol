package com.example.apifutbol.controller;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CountryNotFoundException;
import com.example.apifutbol.model.dto.CountryRequestDTO;
import com.example.apifutbol.model.dto.CountryResponseDTO;
import com.example.apifutbol.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/paises")
public class CountryController {
    private final CountryService service;

    @PostMapping("/crear")
    public ResponseEntity<String> create(@RequestBody CountryRequestDTO countryDTO) throws BadRequestException {
        service.create(countryDTO);
        return new ResponseEntity<>("Se creo el pais correctamente", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CountryResponseDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id={id}")
    public ResponseEntity<CountryResponseDTO> getById(@PathVariable Long id) throws CountryNotFoundException {
        return  ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/nombre={nombre}")
    public ResponseEntity<CountryResponseDTO> getByName(@PathVariable String name) throws CountryNotFoundException {
        return  ResponseEntity.ok(service.getByName(name));
    }

    @PutMapping("/editar")
    public ResponseEntity<String> update(@RequestBody CountryRequestDTO countryDTO) throws CountryNotFoundException, BadRequestException {
        service.update(countryDTO);
        return new ResponseEntity<>("Se edito el pais correctamente",HttpStatus.OK);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id ) throws CountryNotFoundException {
        service.deleteById(id);
        return new ResponseEntity<>("Se elimino el pais", HttpStatus.OK);
    }
}
