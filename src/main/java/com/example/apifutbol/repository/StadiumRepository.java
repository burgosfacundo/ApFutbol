package com.example.apifutbol.repository;

import com.example.apifutbol.model.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StadiumRepository extends JpaRepository<Stadium,Long> {
    @Query("select s from Stadium s where s.name = :name")
    Optional<Stadium> findByName(@Param("name") String name);
}
