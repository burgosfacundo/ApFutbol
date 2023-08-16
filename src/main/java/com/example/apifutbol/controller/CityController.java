package com.example.apifutbol.controller;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CityNotFoundException;
import com.example.apifutbol.model.dto.CityDTO;
import com.example.apifutbol.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/ciudades")
public class CityController {
    private final CityService service;

    @PostMapping("/crear")
    public ResponseEntity<String> create(@RequestBody CityDTO cityDTO) throws BadRequestException {
        service.create(cityDTO);
        return new ResponseEntity<>("Se creo la ciudad correctamente", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id={id}")
    public ResponseEntity<CityDTO> getById(@PathVariable Long id) throws CityNotFoundException {
        return  ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/nombre={nombre}")
    public ResponseEntity<CityDTO> getByName(@PathVariable String name) throws CityNotFoundException {
        return  ResponseEntity.ok(service.getByName(name));
    }

    @PutMapping("/editar")
    public ResponseEntity<String> update(@RequestBody CityDTO cityDTO) throws CityNotFoundException, BadRequestException {
        service.update(cityDTO);
        return new ResponseEntity<>("Se edito la ciudad correctamente",HttpStatus.OK);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id ) throws CityNotFoundException {
        service.deleteById(id);
        return new ResponseEntity<>("Se elimino la ciudad", HttpStatus.OK);
    }
}
