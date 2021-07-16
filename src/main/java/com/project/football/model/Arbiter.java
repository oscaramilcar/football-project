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
@Table(name= "arbiter")
public class Arbiter {
    //private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idArbiter")
    private long idArbiter;

    @Column(name= "name")
    private String name;

    @ManyToOne
    @JoinColumn(name= "idCity")
    private City city;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idArbiter")
    private List<GameMatch> gameMatchList;
}
