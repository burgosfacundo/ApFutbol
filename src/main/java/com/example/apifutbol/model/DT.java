package com.example.apifutbol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DirectoresTecnicos")
public class DT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false,length = 200)
    private String name;

    @OneToOne
    @JoinColumn(name="id_pais")
    private Country country;

    @OneToOne
    @JoinColumn(name="id_ciudad")
    private City city;

    @OneToOne
    @JoinColumn(name="id_equipo")
    private Team team;
}
