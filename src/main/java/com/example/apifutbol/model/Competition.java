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
@Table(name = "competencias")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre",nullable = false,length = 200)
    private String name;

    @OneToOne
    @JoinColumn(name="id_pais")
    private Country country;

    @OneToMany(mappedBy = "competition",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Team> teams= new HashSet<>();

    public void addTeam(Team team) {
        teams.add(team);
        team.setCompetition(this);
    }

    public void removeTeam(Team team) {teams.remove(team);}
}
