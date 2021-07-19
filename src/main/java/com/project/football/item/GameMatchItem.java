package com.project.football.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameMatchItem implements IItem{
    private long idGameMatch;
    private String league;
    private String arbiter;
    private String localTeam;
    private String visitorTeam;
    private String stadium;
    private String date;
    private int localScore;
    private int visitorScore;
    private long idLeague;
    private long idArbiter;
    private long idLocalTeam;
    private long idVisitorTeam;
    private long idStadium;
}
