package com.project.football.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamResponse {
    private long idTeam;
    private String name;
    private String trainer;
    private String image;
    private int wonMatches;
    private int lostMatches;
    private int tiedMatches;
}
