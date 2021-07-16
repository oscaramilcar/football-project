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
@Table(name= "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idCountry")
    private long idCountry;

    @Column(name= "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "idCountry")
    private List<City> cityList;
}
