package com.example.apifutbol.model.dto;

import com.example.apifutbol.model.Country;
import com.example.apifutbol.model.Team;

import java.util.Set;

public record CompetitionResponseDTO(Long id, String name, Country country, Set<Team> teams) {
}
