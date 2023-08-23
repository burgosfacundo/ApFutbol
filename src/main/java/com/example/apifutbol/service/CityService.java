package com.example.apifutbol.service;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CityNotFoundException;
import com.example.apifutbol.model.City;
import com.example.apifutbol.model.Country;
import com.example.apifutbol.model.dto.CityDTO;
import com.example.apifutbol.repository.CityRepository;
import com.example.apifutbol.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CityService {
    private final CityRepository repository;
    private final CountryRepository countryRepository;
    private static final Logger logger = Logger.getLogger(CityService.class);

    public boolean create(CityDTO cityDTO) throws BadRequestException {
        if (repository.findByName(cityDTO.name()).isPresent()) {
            logger.error("Ya existe una ciudad con el nombre: " + cityDTO.name());
            throw new BadRequestException("Ya existe una ciudad con el nombre: " + cityDTO.name());
        }
        if (countryRepository.findById(cityDTO.idCountry()).isEmpty()) {
            logger.error("No existe un pais con el id: " + cityDTO.idCountry());
            throw new BadRequestException("No existe un pais con el id: " + cityDTO.idCountry());
        }
        repository.save(mapToCity(cityDTO));
        logger.info("Se creo una nueva ciudad: " + cityDTO.name());
        return true;
    }

    public List<CityDTO> getAll(){
        var cities = repository.findAll();
        if(cities.isEmpty()){
            logger.info("La tabla Ciudad no tiene registros");
            return null;
        }
        List<CityDTO> dtos = new ArrayList<>();
        for (City city: cities) {
            dtos.add(mapToDTO(city));
        }
        return dtos;
    }

    public CityDTO getById(Long id) throws CityNotFoundException {
        var optional = repository.findById(id);
        if (optional.isEmpty()) {
            logger.error("No existe una ciudad con el id:" + id);
            throw new CityNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public CityDTO getByName(String name) throws CityNotFoundException {
        var optional = repository.findByName(name);
        if (optional.isEmpty()) {
            logger.error("No existe una ciudad con el nombre:" + name);
            throw new CityNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public boolean update(CityDTO cityDTO) throws CityNotFoundException, BadRequestException {
        if (repository.findById(cityDTO.id()).isEmpty()) {
            logger.error("No existe un registro en la tabla Ciudad con el id: " + cityDTO.id());
            throw new CityNotFoundException();
        }
        if (countryRepository.findById(cityDTO.idCountry()).isEmpty()) {
            logger.error("No existe un pais con el id: " + cityDTO.idCountry());
            throw new BadRequestException("No existe un pais con el id: " + cityDTO.idCountry());
        }
        repository.save(mapToCity(cityDTO));
        logger.info("Se modifico el registro con el id: " + cityDTO.id() + " de la tabla Ciudad");
        return true;
    }

    public boolean deleteById(Long id) throws CityNotFoundException {
        if(repository.findById(id).isEmpty()){
            logger.error("No existe un registro en la tabla Ciudad con el id: " + id);
            throw new CityNotFoundException();
        }
        repository.deleteById(id);
        logger.info("Se elimino el registro con el id: " + id + " de la tabla Ciudad");
        return true;
    }

    private City mapToCity(CityDTO cityDTO){
        City city = new City();
        Country country = new Country();
        city.setId(null);
        city.setName(cityDTO.name());
        country.setId(cityDTO.idCountry());
        city.setCountry(country);
        return city;
    }

    private CityDTO mapToDTO(City city){
        return new CityDTO(city.getId(), city.getName(),city.getCountry().getId());
    }
}
