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
@Table(name = "Equipaciones")
public class Kit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "primera",nullable = false)
    private String first;

    @Column(name = "segunda",nullable = false)
    private String second;

    @Column(name = "tercera")
    private String third;

    @OneToOne(mappedBy = "kit")
    private Team team;
}
