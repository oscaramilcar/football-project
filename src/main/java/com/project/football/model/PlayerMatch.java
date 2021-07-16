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
@Table(name="playerMatch")
public class PlayerMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlayerMatch")
    private long idPlayerMatch;

    @ManyToOne
    @JoinColumn(name= "idPlayer")
    private Player player;

    @Column(name = "goals")
    private int goals;

    @ManyToOne
    @JoinColumn(name = "idGameMatch")
    private GameMatch gameMatch;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPlayerMatch")
    private List<Card> cardList;
}
