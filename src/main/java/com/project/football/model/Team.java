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
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idTeam")
    private long idTeam;

    @Column(name= "name")
    private String name;

    @Column(name= "trainer")
    private String trainer;

    @Column(name = "image")
    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTeamPlayer")
    private List<Player> playerList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdTeamGame")
    private List<GameMatch> gameMatchList;
}
