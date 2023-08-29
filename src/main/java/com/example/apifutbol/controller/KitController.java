package com.example.apifutbol.controller;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.KitNotFoundException;
import com.example.apifutbol.model.dto.KitDTO;
import com.example.apifutbol.service.KitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/kits")
public class KitController {
    private final KitService service;

    @PostMapping("/crear")
    public ResponseEntity<String> create(@RequestBody KitDTO kitDTO) throws BadRequestException {
        service.create(kitDTO);
        return new ResponseEntity<>("Se creo el Kit correctamente", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<KitDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/id={id}")
    public ResponseEntity<KitDTO> getById(@PathVariable Long id) throws KitNotFoundException {
        return  ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/nombre={nombre}")
    public ResponseEntity<KitDTO> getByName(@PathVariable Long idTeam) throws KitNotFoundException {
        return  ResponseEntity.ok(service.getByIdTeam(idTeam));
    }

    @PutMapping("/editar")
    public ResponseEntity<String> update(@RequestBody KitDTO kitDTO) throws KitNotFoundException, BadRequestException {
        service.update(kitDTO);
        return new ResponseEntity<>("Se edito el Kit correctamente",HttpStatus.OK);
    }


    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws KitNotFoundException {
        service.deleteById(id);
        return new ResponseEntity<>("Se elimino el Kit", HttpStatus.OK);
    }
}
