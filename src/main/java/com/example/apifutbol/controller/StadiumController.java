package com.example.apifutbol.controller;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.StadiumNotFoundException;
import com.example.apifutbol.model.dto.StadiumRequestDTO;
import com.example.apifutbol.model.dto.StadiumResponseDTO;
import com.example.apifutbol.service.StadiumService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/stadiums")
public class StadiumController {
    private final StadiumService service;

    @PostMapping("/crear")
    public ResponseEntity<String> create(@RequestBody StadiumRequestDTO stadiumRequestDTO) throws BadRequestException {
        service.create(stadiumRequestDTO);
        return new ResponseEntity<>("Se creo el Estadio correctamente", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StadiumResponseDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id={id}")
    public ResponseEntity<StadiumResponseDTO> getById(@PathVariable Long id) throws StadiumNotFoundException {
        return  ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/nombre={nombre}")
    public ResponseEntity<StadiumResponseDTO> getByName(@PathVariable String name) throws StadiumNotFoundException {
        return  ResponseEntity.ok(service.getByName(name));
    }

    @PutMapping("/editar")
    public ResponseEntity<String> update(@RequestBody StadiumRequestDTO stadiumRequestDTO) throws StadiumNotFoundException, BadRequestException {
        service.update(stadiumRequestDTO);
        return new ResponseEntity<>("Se edito el Estadio correctamente",HttpStatus.OK);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id ) throws StadiumNotFoundException {
        service.deleteById(id);
        return new ResponseEntity<>("Se elimino el Estadio", HttpStatus.OK);
    }
}
