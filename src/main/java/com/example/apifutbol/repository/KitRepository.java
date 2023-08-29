package com.example.apifutbol.repository;

import com.example.apifutbol.model.Kit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KitRepository extends JpaRepository<Kit,Long> {

    @Query("select k from Kit k where k.idTeam = :idTeam")
    Optional<Kit> findByIdTeam(@Param("idTeam") Long idTeam);
}
