package com.example.apifutbol.repository;

import com.example.apifutbol.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {
    @Query("select c from City c where c.name = :name")
    Optional<City> findByName(@Param("name") String name);
}
