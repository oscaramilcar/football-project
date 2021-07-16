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
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPosition")
    private long idPosition;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPosition")
    private List<Player> playerList;
}
