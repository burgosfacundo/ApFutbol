package com.example.apifutbol.repository;

import com.example.apifutbol.model.Kit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitRepository extends JpaRepository<Kit,Long> {
}
