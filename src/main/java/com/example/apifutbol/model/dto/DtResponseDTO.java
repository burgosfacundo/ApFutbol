package com.example.apifutbol.model.dto;

import com.example.apifutbol.model.City;
import com.example.apifutbol.model.Country;

public record DtResponseDTO(Long id, String name, Country country, City city) {
}
