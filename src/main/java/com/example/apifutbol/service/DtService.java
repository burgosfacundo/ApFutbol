package com.example.apifutbol.service;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.DTNotFoundException;
import com.example.apifutbol.model.City;
import com.example.apifutbol.model.Country;
import com.example.apifutbol.model.DT;
import com.example.apifutbol.model.dto.DtResponseDTO;
import com.example.apifutbol.model.dto.DtRequestDTO;
import com.example.apifutbol.repository.CityRepository;
import com.example.apifutbol.repository.CountryRepository;
import com.example.apifutbol.repository.DTRepository;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DtService {
    private final DTRepository repository;
    private final CountryRepository countryRepository;
    private  final CityRepository cityRepository;
    private static final Logger logger = Logger.getLogger(CityService.class);

    public boolean create(DtRequestDTO dtRequestDTO) throws BadRequestException {
        if (repository.findByName(dtRequestDTO.name()).isPresent()) {
            logger.error("Ya existe un DT con el nombre: " + dtRequestDTO.name());
            throw new BadRequestException("Ya existe un DT con el nombre: " + dtRequestDTO.name());
        }
        if (countryRepository.findById(dtRequestDTO.idCountry()).isEmpty()) {
            logger.error("No existe un pais con el id: " + dtRequestDTO.idCountry());
            throw new BadRequestException("No existe un pais con el id: " + dtRequestDTO.idCountry());
        }
        if (cityRepository.findById(dtRequestDTO.idCity()).isEmpty()) {
            logger.error("No existe una ciudad con el id: " + dtRequestDTO.idCity());
            throw new BadRequestException("No existe una ciudad con el id: " + dtRequestDTO.idCity());
        }
        repository.save(mapToDT(dtRequestDTO));
        logger.info("Se creo un nuevo DT: " + dtRequestDTO.name());
        return true;
    }

    public List<DtResponseDTO> getAll(){
        var dts = repository.findAll();
        if(dts.isEmpty()){
            logger.info("La tabla DT no tiene registros");
            return null;
        }
        List<DtResponseDTO> dtos = new ArrayList<>();
        for (DT dt: dts) {
            dtos.add(mapToDTO(dt));
        }
        return dtos;
    }

    public DtResponseDTO getById(Long id) throws DTNotFoundException {
        var optional = repository.findById(id);
        if (optional.isEmpty()) {
            logger.error("No existe un DT con el id:" + id);
            throw new DTNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public DtResponseDTO getByName(String name) throws DTNotFoundException {
        var optional = repository.findByName(name);
        if (optional.isEmpty()) {
            logger.error("No existe un DT con el nombre:" + name);
            throw new DTNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public boolean update(DtRequestDTO dtRequestDTO) throws DTNotFoundException, BadRequestException {
        if (repository.findById(dtRequestDTO.id()).isEmpty()) {
            logger.error("No existe un registro en la tabla DT con el id: " + dtRequestDTO.id());
            throw new DTNotFoundException();
        }
        if (repository.findByName(dtRequestDTO.name()).isPresent()) {
            logger.error("Ya existe un DT con el nombre: " + dtRequestDTO.name());
            throw new BadRequestException("Ya existe un DT con el nombre: " + dtRequestDTO.name());
        }
        if (countryRepository.findById(dtRequestDTO.idCountry()).isEmpty()) {
            logger.error("No existe un pais con el id: " + dtRequestDTO.idCountry());
            throw new BadRequestException("No existe un pais con el id: " + dtRequestDTO.idCountry());
        }
        if (cityRepository.findById(dtRequestDTO.idCity()).isEmpty()) {
            logger.error("No existe una ciudad con el id: " + dtRequestDTO.idCity());
            throw new BadRequestException("No existe una ciudad con el id: " + dtRequestDTO.idCity());
        }

        repository.save(mapToDT(dtRequestDTO));
        logger.info("Se modifico el registro con el id: " + dtRequestDTO.id() + " de la tabla DT");
        return true;
    }

    public boolean deleteById(Long id) throws DTNotFoundException {
        if(repository.findById(id).isEmpty()){
            logger.error("No existe un registro en la tabla DT con el id: " + id);
            throw new DTNotFoundException();
        }
        repository.deleteById(id);
        logger.info("Se elimino el registro con el id: " + id + " de la tabla DT");
        return true;
    }

    private DT mapToDT(DtRequestDTO dtRequestDTO){
        DT dt = new DT();
        dt.setId(null);
        dt.setName(dtRequestDTO.name());
        var country = new Country();
        country.setId(dtRequestDTO.idCountry());
        dt.setCountry(country);
        var city = new City();
        city.setId(dtRequestDTO.idCity());
        dt.setCity(city);
        return dt;
    }

    private DtResponseDTO mapToDTO(DT dt){
        return new DtResponseDTO(dt.getId(), dt.getName(),dt.getCountry(),dt.getCity());
    }
}
