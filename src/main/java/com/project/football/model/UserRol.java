package com.project.football.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "userRol")
public class UserRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUserRol")
    private long idUserRol;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}
