package com.example.apifutbol.controller;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.DTNotFoundException;
import com.example.apifutbol.model.dto.DtRequestDTO;
import com.example.apifutbol.model.dto.DtResponseDTO;
import com.example.apifutbol.service.DtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/dts")
public class DtController {
    private final DtService service;

    @PostMapping("/crear")
    public ResponseEntity<String> create(@RequestBody DtRequestDTO dtRequestDTO) throws BadRequestException {
        service.create(dtRequestDTO);
        return new ResponseEntity<>("Se creo el DT correctamente", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DtResponseDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id={id}")
    public ResponseEntity<DtResponseDTO> getById(@PathVariable Long id) throws DTNotFoundException {
        return  ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/nombre={nombre}")
    public ResponseEntity<DtResponseDTO> getByName(@PathVariable String name) throws DTNotFoundException {
        return  ResponseEntity.ok(service.getByName(name));
    }

    @PutMapping("/editar")
    public ResponseEntity<String> update(@RequestBody DtRequestDTO dtRequestDTO) throws DTNotFoundException, BadRequestException {
        service.update(dtRequestDTO);
        return new ResponseEntity<>("Se edito el DT correctamente",HttpStatus.OK);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id ) throws DTNotFoundException {
        service.deleteById(id);
        return new ResponseEntity<>("Se elimino el DT", HttpStatus.OK);
    }
}
