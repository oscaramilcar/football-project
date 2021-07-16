package com.project.football.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlayer")
    private long idPlayer;

    @Column(name= "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "idTeam")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "idPosition")
    private Position position;

    @Column(name = "image")
    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPlayer")
    private List<PlayerMatch> playerMatchList;
}
