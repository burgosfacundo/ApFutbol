package com.example.apifutbol.repository;

import com.example.apifutbol.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
