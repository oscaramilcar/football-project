package com.project.football.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerItem implements IItem{
    private long idPlayer;
    private String firstName;
    private String lastName;
    private int age;
    private String team;
    private String position;
    private String image;
    private long idTeam;
    private long idPosition;
}
