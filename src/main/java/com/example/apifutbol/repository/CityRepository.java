package com.example.apifutbol.repository;

import com.example.apifutbol.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
