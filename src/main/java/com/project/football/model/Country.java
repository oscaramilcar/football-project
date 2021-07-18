package com.project.football.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "country")
public class Country implements Serializable {
    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idCountry")
    private String idCountry;

    @Column(name= "name")
    private String name;

/*    @JsonIgnoreProperties(value={"country", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "idCountry")
    private List<City> cityList;*/
}
