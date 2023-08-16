package com.example.apifutbol.model.dto;

import com.example.apifutbol.model.City;

import java.util.Set;

public record CountryResponseDTO(Long id, String name, Set<City> cities) {
}
