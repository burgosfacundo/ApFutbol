package com.example.apifutbol.service;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.KitNotFoundException;
import com.example.apifutbol.model.*;
import com.example.apifutbol.model.dto.KitDTO;
import com.example.apifutbol.repository.KitRepository;
import com.example.apifutbol.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class KitService {
    private final KitRepository kitRepository;
    private final TeamRepository teamRepository;
    private static final Logger logger = Logger.getLogger(KitService.class);

    public boolean create(KitDTO dto) throws BadRequestException {
        if(kitRepository.findById(dto.id()).isPresent()){
            logger.error("Ya existe un Kit con el id: " + dto.id());
            throw new BadRequestException("Ya existe un Kit con el id: " + dto.id());
        }
        if (teamRepository.findById(dto.idTeam()).isEmpty()){
            logger.error("No existe un equipo con el id: " + dto.idTeam());
            throw new BadRequestException("No existe un equipo con el id: " + dto.idTeam());
        }

        kitRepository.save(mapToKit(dto));
        logger.info("Se creo un nuevo Kit: " + dto.id());
        return true;
    }

    public List<KitDTO> getAll(){
        var kits = kitRepository.findAll();
        if(kits.isEmpty()){
            logger.info("La tabla Kit no tiene registros");
            return null;
        }
        List<KitDTO> dtos = new ArrayList<>();
        for (Kit kit: kits) {
            dtos.add(mapToDTO(kit));
        }
        return dtos;
    }

    public KitDTO getById(Long id) throws KitNotFoundException {
        var optional = kitRepository.findById(id);
        if (optional.isEmpty()) {
            logger.error("No existe un Kit con el id:" + id);
            throw new KitNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public KitDTO getByIdTeam(Long idTeam) throws KitNotFoundException {
        var optional = kitRepository.findByIdTeam(idTeam);
        if (optional.isEmpty()) {
            logger.error("No existe un Kit con el id de Equipo:" + idTeam);
            throw new KitNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public boolean update(KitDTO kitDTO) throws KitNotFoundException, BadRequestException {
        if (kitRepository.findById(kitDTO.id()).isEmpty()) {
            logger.error("No existe un registro en la tabla Kit con el id: " + kitDTO.id());
            throw new KitNotFoundException();
        }
        if (kitRepository.findByIdTeam(kitDTO.idTeam()).isPresent()) {
            logger.error("Ya existe un Kit con el id de Equipo: " + kitDTO.idTeam());
            throw new BadRequestException("Ya existe un Kit con el id de Equipo: " + kitDTO.idTeam());
        }

        kitRepository.save(mapToKit(kitDTO));
        logger.info("Se modifico el registro con el id: " + kitDTO.id() + " de la tabla Kit");
        return true;
    }

    public boolean deleteById(Long id) throws KitNotFoundException {
        if(kitRepository.findById(id).isEmpty()){
            logger.error("No existe un registro en la tabla Kit con el id: " + id);
            throw new KitNotFoundException();
        }
        kitRepository.deleteById(id);
        logger.info("Se elimino el registro con el id: " + id + " de la tabla Kit");
        return true;
    }

    private Kit mapToKit(KitDTO kitDTO){
        Kit kit = new Kit();

        kit.setId(null);
        kit.setFirst(kitDTO.first());
        kit.setSecond(kitDTO.second());
        kit.setThird(kitDTO.third());

        Team team = new Team();
        team.setId(kitDTO.idTeam());
        kit.setTeam(team);

        return kit;
    }

    private KitDTO mapToDTO(Kit kit){
        return new KitDTO(kit.getId(), kit.getFirst(),kit.getSecond(),kit.getThird(),kit.getTeam().getId());
    }
}
