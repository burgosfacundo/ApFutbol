package com.example.apifutbol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipos")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false,length = 100)
    private String name;

    @Column(name = "escudo",nullable = false)
    private String logo;

    @Column(name = "direccion",length = 100)
    private String address;

    @Column(name = "sitioWeb",length = 100)
    private String website;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "fundacion",nullable = false)
    private Date fundation;

    @Column(name = "alias")
    private Date alias;

    @OneToOne
    @JoinColumn(name="id_pais")
    private Country country;

    @OneToOne
    @JoinColumn(name="id_ciudad")
    private City city;

    @OneToOne
    @JoinColumn(name="id_equipacion")
    private Kit kit;

    @OneToOne
    @JoinColumn(name="id_dt")
    private DT dt;

    @OneToOne
    @JoinColumn(name="id_estadio")
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(name = "id_competicion",nullable = false)
    private Competition competition;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Player> players= new HashSet<>();

    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }

    public void removePlayer(Player player) {players.remove(player);}
}
