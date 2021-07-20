package com.project.football.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameMatchRequest {
    private long idGameMatch;
    private long idLeague;
    private long idArbiter;
    private long idLocalTeam;
    private long idVisitorTeam;
    private long idStadium;
    private String date;
}