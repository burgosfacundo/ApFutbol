package com.example.apifutbol.service;

import com.example.apifutbol.exception.BadRequestException;
import com.example.apifutbol.exception.CompetitionNotFoundException;
import com.example.apifutbol.model.Competition;
import com.example.apifutbol.model.Country;
import com.example.apifutbol.model.dto.CompetitionRequestDTO;
import com.example.apifutbol.model.dto.CompetitionResponseDTO;
import com.example.apifutbol.repository.CompetitionRepository;
import com.example.apifutbol.repository.CountryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CompetitionService {

    private final CompetitionRepository repository;
    private final CountryRepository countryRepository;

    private static final Logger logger = Logger.getLogger(CompetitionService.class);

    public boolean create(CompetitionRequestDTO competitionRequestDTO) throws BadRequestException {
        if (repository.findByName(competitionRequestDTO.name()).isPresent()) {
            logger.error("Ya existe una competicion con el nombre: " + competitionRequestDTO.name());
            throw new BadRequestException("Ya existe una competicion con el nombre: " + competitionRequestDTO.name());
        }
        if (countryRepository.findById(competitionRequestDTO.idCountry()).isEmpty()) {
            logger.error("No existe un pais con el id: " + competitionRequestDTO.idCountry());
            throw new BadRequestException("No existe un pais con el id: " + competitionRequestDTO.idCountry());
        }
        repository.save(mapToCompetition(competitionRequestDTO));
        logger.info("Se creo una nueva competencia: " + competitionRequestDTO.name());
        return true;
    }

    public List<CompetitionResponseDTO> getAll(){
        var competitions = repository.findAll();
        if(competitions.isEmpty()){
            logger.info("La tabla Competencias no tiene registros");
            return null;
        }
        List<CompetitionResponseDTO> dtos = new ArrayList<>();
        for (Competition competition: competitions) {
            dtos.add(mapToDTO(competition));
        }
        return dtos;
    }

    public CompetitionResponseDTO getById(Long id) throws CompetitionNotFoundException {
        var optional = repository.findById(id);
        if (optional.isEmpty()) {
            logger.error("No existe una competencia con el id:" + id);
            throw new CompetitionNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public CompetitionResponseDTO getByName(String name) throws CompetitionNotFoundException {
        var optional = repository.findByName(name);
        if (optional.isEmpty()) {
            logger.error("No existe una competencia con el nombre:" + name);
            throw new CompetitionNotFoundException();
        }
        return mapToDTO(optional.get());
    }

    public boolean update(CompetitionRequestDTO competitionRequestDTO) throws CompetitionNotFoundException, BadRequestException {
        if (repository.findById(competitionRequestDTO.id()).isEmpty()) {
            logger.error("No existe un registro en la tabla Competencias con el id: " + competitionRequestDTO.id());
            throw new CompetitionNotFoundException();
        }
        if (countryRepository.findById(competitionRequestDTO.idCountry()).isEmpty()) {
            logger.error("No existe una competencia con el id: " + competitionRequestDTO.idCountry());
            throw new BadRequestException("No existe una competencia con el id: " + competitionRequestDTO.idCountry());
        }

        repository.save(mapToCompetition(competitionRequestDTO));
        logger.info("Se modifico el registro con el id: " + competitionRequestDTO.id() + " de la tabla Competencias");
        return true;
    }

    public boolean deleteById(Long id) throws CompetitionNotFoundException {
        if(repository.findById(id).isEmpty()){
            logger.error("No existe un registro en la tabla Competencias con el id: " + id);
            throw new CompetitionNotFoundException();
        }
        repository.deleteById(id);
        logger.info("Se elimino el registro con el id: " + id + " de la tabla Competencias");
        return true;
    }

    private Competition mapToCompetition(CompetitionRequestDTO competitionRequestDTO){
        Competition competition = new Competition();
        Country country = new Country();
        competition.setId(null);
        competition.setName(competitionRequestDTO.name());
        country.setId(competitionRequestDTO.idCountry());
        competition.setCountry(country);
        return competition;
    }

    private CompetitionResponseDTO mapToDTO(Competition competition){
        return new CompetitionResponseDTO(competition.getId(), competition.getName(),
                competition.getCountry(),competition.getTeams());
    }
}
