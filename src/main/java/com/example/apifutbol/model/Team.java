package com.example.apifutbol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Equipos")
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
}
