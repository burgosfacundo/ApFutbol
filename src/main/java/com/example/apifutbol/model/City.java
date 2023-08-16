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
@Table(name = "Ciudades")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false,length = 200)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_pais",nullable = false)
    private Country country;

    @OneToOne(mappedBy = "city")
    private Stadium stadium;

    @OneToOne(mappedBy = "city")
    private Player player;

    @OneToOne(mappedBy = "city")
    private Team team;

    @OneToOne(mappedBy = "city")
    private DT dt;
}
