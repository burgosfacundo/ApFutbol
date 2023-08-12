package com.example.apifutbol.repository;

import com.example.apifutbol.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition,Long> {
}
