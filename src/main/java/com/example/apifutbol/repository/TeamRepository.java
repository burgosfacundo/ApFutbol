package com.example.apifutbol.repository;

import com.example.apifutbol.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
