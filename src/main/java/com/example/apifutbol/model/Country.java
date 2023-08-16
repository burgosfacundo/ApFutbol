package com.example.apifutbol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paises")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false,length = 200)
    private String name;

    @OneToOne(mappedBy = "country")
    private Competition competition;

    @OneToOne(mappedBy = "country")
    private Team team;

    @OneToOne(mappedBy = "country")
    private DT dt;

    @OneToOne(mappedBy = "country")
    private Player player;

    @OneToOne(mappedBy = "country")
    private Stadium stadium;

    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<City> cities= new HashSet<>();

    public void addCity(City city) {
        cities.add(city);
        city.setCountry(this);
    }

    public void removeCity(City city) {cities.remove(city);}
}
