package com.example.apifutbol.repository;

import com.example.apifutbol.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country,Long> {
    @Query("select c from Country c where c.name = :name")
    Optional<Country> findByName(@Param("name") String name);
}
