package com.project.football.item;

public interface GolsByGameMatchItem {
    Long getIdGameMatch();
    Long getIdLeague();
    Long getIdArbiter();
    Long getIdLocalTeam();
    Long getIdVisitorTeam();
    String getDate();
    int getLocalScore();
    int getVisitorScore();
    Long getIdStadium();
}
