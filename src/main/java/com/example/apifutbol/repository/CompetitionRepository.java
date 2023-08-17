package com.example.apifutbol.repository;

import com.example.apifutbol.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition,Long> {
    @Query("select c from Competition c where c.name = :name")
    Optional<Competition> findByName(@Param("name") String name);
}
