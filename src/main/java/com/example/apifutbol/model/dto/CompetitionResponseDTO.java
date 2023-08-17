package com.example.apifutbol.model.dto;

import com.example.apifutbol.model.Team;

import java.util.Set;

public record CompetitionResponseDTO(Long id, String name, Long idPais, Set<Team> teams) {
}
