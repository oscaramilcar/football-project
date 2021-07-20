package com.project.football.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "gameMatch")
public class GameMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idGameMatch")
    private long idGameMatch;

    @ManyToOne
    @JoinColumn(name = "idLeague")
    private League league;

    @ManyToOne
    @JoinColumn(name= "idArbiter")
    private Arbiter arbiter;

    @ManyToOne
    @JoinColumn(name = "idLocalTeam")
    private Team localTeam;

    @ManyToOne
    @JoinColumn(name= "idVisitorTeam")
    private Team visitorTeam;

    @ManyToOne
    @JoinColumn(name= "idStadium")
    private Stadium stadium;

    @Column(name= "date")
    private String date;

    @Column(name= "localScore")
    private int localScore;

    @Column(name= "visitorScore")
    private int visitorScore;
}
