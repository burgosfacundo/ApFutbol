package com.example.apifutbol.controller;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CompetitionNotFoundException;
import com.example.apifutbol.model.dto.CompetitionRequestDTO;
import com.example.apifutbol.model.dto.CompetitionResponseDTO;
import com.example.apifutbol.service.CompetitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/competencias")
public class CompetitionController {
    private final CompetitionService service;

    @PostMapping("/crear")
    public ResponseEntity<String> create(@RequestBody CompetitionRequestDTO competitionRequestDTO) throws BadRequestException {
        service.create(competitionRequestDTO);
        return new ResponseEntity<>("Se creo la competencia correctamente", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id={id}")
    public ResponseEntity<CompetitionResponseDTO> getById(@PathVariable Long id) throws CompetitionNotFoundException {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/nombre={nombre}")
    public ResponseEntity<CompetitionResponseDTO> getByName(@PathVariable String name) throws CompetitionNotFoundException {
        return  ResponseEntity.ok(service.getByName(name));
    }

    @PutMapping("/editar")
    public ResponseEntity<String> update(@RequestBody CompetitionRequestDTO competitionRequestDTO) throws CompetitionNotFoundException, BadRequestException {
        service.update(competitionRequestDTO);
        return new ResponseEntity<>("Se edito la competencia correctamente",HttpStatus.OK);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id ) throws CompetitionNotFoundException {
        service.deleteById(id);
        return new ResponseEntity<>("Se elimino la competencia", HttpStatus.OK);
    }
}
