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
@Table(name = "Jugadores")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false,length = 200)
    private String name;

    @Column(name = "posicion",nullable = false,length = 200)
    private String position;

    @Column(name = "goles",nullable = false)
    private Integer goals;

    @Column(name = "asistencias",nullable = false)
    private Integer assists;

    @Column(name = "partidosJugados",nullable = false)
    private Integer gamesPlayed;
}
