package com.project.football.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerRequest {
    private long idPlayer;
    private String firstName;
    private String lastName;
    private int age;
    private long idTeam;
    private long idPosition;
    private String image;
}