package com.project.football.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Year;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idLeague")
    private long idLeague;

    @Column(name= "name")
    private String name;

    @Column(name= "year")
    private Year year;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idLeague")
    private List<GameMatch> gameMatchList;
}
