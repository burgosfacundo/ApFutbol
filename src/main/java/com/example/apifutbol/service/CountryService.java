package com.example.apifutbol.service;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CountryNotFoundException;
import com.example.apifutbol.model.Country;
import com.example.apifutbol.model.dto.CountryRequestDTO;
import com.example.apifutbol.model.dto.CountryResponseDTO;
import com.example.apifutbol.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CountryService {
    private final CountryRepository repository;
    private static final Logger logger = Logger.getLogger(CountryService.class);

    public boolean create(CountryRequestDTO countryRequestDTO) throws BadRequestException {
        if (repository.findByName(countryRequestDTO.name()).isPresent()) {
            logger.error("Ya existe un pais con el nombre: " + countryRequestDTO.name());
            throw new BadRequestException("Ya existe un pais con el nombre: " + countryRequestDTO.name());
        }
        repository.save(mapToCountry(countryRequestDTO));
        logger.info("Se creo un nuevo pais: " + countryRequestDTO.name());
        return true;
    }

    public List<CountryResponseDTO> getAll(){
        var countries = repository.findAll();
        if(countries.isEmpty()){
            logger.info("La tabla Pais no tiene registros");
            return null;
        }
        List<CountryResponseDTO> dtos = new ArrayList<>();
        for (Country country: countries) {
            dtos.add(mapToDTO(country));
        }
        return dtos;
    }

    public CountryResponseDTO getById(Long id) throws CountryNotFoundException {
        var optional = repository.findById(id);
        if (optional.isEmpty()) {
            logger.error("No existe un pais con el id:" + id);
            throw new CountryNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public CountryResponseDTO getByName(String name) throws CountryNotFoundException {
        var optional = repository.findByName(name);
        if (optional.isEmpty()) {
            logger.error("No existe un pais con el nombre:" + name);
            throw new CountryNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public boolean update(CountryRequestDTO countryDTO) throws CountryNotFoundException, BadRequestException {
        if (repository.findById(countryDTO.id()).isEmpty()) {
            logger.error("No existe un registro en la tabla Pais con el id: " + countryDTO.id());
            throw new CountryNotFoundException();
        }
        if (repository.findByName(countryDTO.name()).isPresent()) {
            logger.error("Ya existe un pais con el nombre: " + countryDTO.name());
            throw new BadRequestException("Ya existe un pais con el nombre: " + countryDTO.name());
        }
        repository.save(mapToCountry(countryDTO));
        logger.info("Se modifico el registro con el id: " + countryDTO.id() + " de la tabla Pais");
        return true;
    }

    public boolean deleteById(Long id) throws CountryNotFoundException {
        if(repository.findById(id).isEmpty()){
            logger.error("No existe un registro en la tabla Pais con el id: " + id);
            throw new CountryNotFoundException();
        }
        repository.deleteById(id);
        logger.info("Se elimino el registro con el id: " + id + " de la tabla Pais");
        return true;
    }

    private Country mapToCountry(CountryRequestDTO countryRequestDTO){
        Country country = new Country();

        country.setId(null);
        country.setName(countryRequestDTO.name());

        return country;
    }

    private CountryResponseDTO mapToDTO(Country country){
        return new CountryResponseDTO(country.getId(), country.getName(),country.getCities());
    }
}
