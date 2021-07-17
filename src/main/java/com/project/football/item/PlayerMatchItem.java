package com.project.football.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerMatchItem implements IItem{
    private long idPlayerMatch;
    private String player;
    private int goals;
    private String league;
    private String date;
    private String team;
    private long idGameMatch;
    private long idPlayer;
}
