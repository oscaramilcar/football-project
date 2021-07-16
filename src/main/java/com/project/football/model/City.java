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
@Table(name= "citie")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idCity")
    private long idCity;

    @Column(name= "name")
    private String name;

    @ManyToOne
    @JoinColumn(name= "idCountry")
    private Country country;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCityArbiter")
    private List<Arbiter> arbiterList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCityStadium")
    private List<Stadium> stadiumList;
}
