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
@Table(name="rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol")
    private long idRol;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRol")
    private List<UserRol> userRolList;
}
