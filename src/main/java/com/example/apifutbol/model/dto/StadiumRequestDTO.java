package com.example.apifutbol.model.dto;

public record StadiumRequestDTO(Long id, String name,String capacity,String typeGrass,Long idCountry, Long idCity,Long idTeam) {
}
