package com.example.apifutbol.service;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.StadiumNotFoundException;
import com.example.apifutbol.model.*;
import com.example.apifutbol.model.dto.StadiumRequestDTO;
import com.example.apifutbol.model.dto.StadiumResponseDTO;
import com.example.apifutbol.repository.CityRepository;
import com.example.apifutbol.repository.CountryRepository;
import com.example.apifutbol.repository.StadiumRepository;
import com.example.apifutbol.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class StadiumService {
    private final StadiumRepository stadiumRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final TeamRepository teamRepository;
    private static final Logger logger = Logger.getLogger(StadiumService.class);

    public boolean create(StadiumRequestDTO dto) throws BadRequestException {
        if(stadiumRepository.findById(dto.id()).isPresent()){
            logger.error("Ya existe un Estadio con el id: " + dto.id());
            throw new BadRequestException("Ya existe un Estadio con el id: " + dto.id());
        }
        if(stadiumRepository.findByName(dto.name()).isPresent()){
            logger.error("Ya existe un Estadio con el nombre: " + dto.id());
            throw new BadRequestException("Ya existe un Estadio con el nombre: " + dto.id());
        }
        if (countryRepository.findById(dto.idCountry()).isEmpty()){
            logger.error("No existe un pais con el id: " + dto.idTeam());
            throw new BadRequestException("No existe un pais con el id: " + dto.idTeam());
        }
        if (cityRepository.findById(dto.idCity()).isEmpty()){
            logger.error("No existe una ciudad con el id: " + dto.idTeam());
            throw new BadRequestException("No existe una ciudad con el id: " + dto.idTeam());
        }
        if (teamRepository.findById(dto.idTeam()).isEmpty()){
            logger.error("No existe un equipo con el id: " + dto.idTeam());
            throw new BadRequestException("No existe un equipo con el id: " + dto.idTeam());
        }

        stadiumRepository.save(mapToStadium(dto));
        logger.info("Se creo un nuevo Estadio: " + dto.id());
        return true;
    }

    public List<StadiumResponseDTO> getAll(){
        var stadiums = stadiumRepository.findAll();
        if(stadiums.isEmpty()){
            logger.info("La tabla Estadios no tiene registros");
            return null;
        }
        List<StadiumResponseDTO> dtos = new ArrayList<>();
        for (Stadium stadium: stadiums) {
            dtos.add(mapToDTO(stadium));
        }
        return dtos;
    }

    public StadiumResponseDTO getById(Long id) throws StadiumNotFoundException {
        var optional = stadiumRepository.findById(id);
        if (optional.isEmpty()) {
            logger.error("No existe un Estadio con el id:" + id);
            throw new StadiumNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public StadiumResponseDTO getByName(String name) throws StadiumNotFoundException {
        var optional = stadiumRepository.findByName(name);
        if (optional.isEmpty()) {
            logger.error("No existe un Estadio con el nombre:" + name);
            throw new StadiumNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public boolean update(StadiumRequestDTO stadiumRequestDTO) throws StadiumNotFoundException, BadRequestException {
        if(stadiumRepository.findById(stadiumRequestDTO.id()).isEmpty()){
            logger.error("No existe un registro en la tabla Estadio con el id: " + stadiumRequestDTO.id());
            throw new StadiumNotFoundException();
        }
        if(stadiumRepository.findByName(stadiumRequestDTO.name()).isPresent()){
            logger.error("Ya existe un Estadio con el nombre: " + stadiumRequestDTO.id());
            throw new BadRequestException("Ya existe un Estadio con el nombre: " + stadiumRequestDTO.id());
        }
        if (countryRepository.findById(stadiumRequestDTO.idCountry()).isEmpty()){
            logger.error("No existe un pais con el id: " + stadiumRequestDTO.idTeam());
            throw new BadRequestException("No existe un pais con el id: " + stadiumRequestDTO.idTeam());
        }
        if (cityRepository.findById(stadiumRequestDTO.idCity()).isEmpty()){
            logger.error("No existe una ciudad con el id: " + stadiumRequestDTO.idTeam());
            throw new BadRequestException("No existe una ciudad con el id: " + stadiumRequestDTO.idTeam());
        }
        if (teamRepository.findById(stadiumRequestDTO.idTeam()).isEmpty()){
            logger.error("No existe un equipo con el id: " + stadiumRequestDTO.idTeam());
            throw new BadRequestException("No existe un equipo con el id: " + stadiumRequestDTO.idTeam());
        }

        stadiumRepository.save(mapToStadium(stadiumRequestDTO));
        logger.info("Se modifico el registro con el id: " + stadiumRequestDTO.id() + " de la tabla Estadio");
        return true;
    }

    public boolean deleteById(Long id) throws StadiumNotFoundException {
        if(stadiumRepository.findById(id).isEmpty()){
            logger.error("No existe un registro en la tabla Estadio con el id: " + id);
            throw new StadiumNotFoundException();
        }
        stadiumRepository.deleteById(id);
        logger.info("Se elimino el registro con el id: " + id + " de la tabla Estadio");
        return true;
    }

    private Stadium mapToStadium(StadiumRequestDTO stadiumRequestDTO){
        Stadium stadium = new Stadium();

        stadium.setId(null);
        stadium.setName(stadiumRequestDTO.name());
        stadium.setCapacity(stadiumRequestDTO.capacity());
        stadium.setTypeGrass(stadiumRequestDTO.typeGrass());

        Team team = new Team();
        team.setId(stadiumRequestDTO.idTeam());
        stadium.setTeam(team);

        City city = new City();
        city.setId(stadiumRequestDTO.idCity());
        stadium.setCity(city);

        Country country = new Country();
        country.setId(stadiumRequestDTO.idCountry());
        stadium.setCountry(country);

        return stadium;
    }

    private StadiumResponseDTO mapToDTO(Stadium stadium){
        return new StadiumResponseDTO(stadium.getId(), stadium.getName(),stadium.getCapacity(),stadium.getTypeGrass(),
                stadium.getCountry().getName(),stadium.getCity().getName(),stadium.getTeam().getName());
    }
}
