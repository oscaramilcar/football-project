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
@Table(name= "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idCard")
    private long idCard;

    @Column(name= "color", columnDefinition = "enum('YELLOW','RED')")
    private String color;

    @Column(name = "amount")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "idPlayerMatch")
    private PlayerMatch playerMatch;
}
