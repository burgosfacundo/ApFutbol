package com.example.apifutbol.repository;

import com.example.apifutbol.model.DT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DTRepository extends JpaRepository<DT,Long> {
    @Query("select c from DT d where d.name = :name")
    Optional<DT> findByName(@Param("name") String name);
}
