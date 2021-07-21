package com.project.football.services;

import com.project.football.item.TeamClassificationItem;
import com.project.football.response.TeamResponse;

import java.util.List;

public interface IStatisticReport {
    List<TeamClassificationItem> getGoalsByMatchByLeague(long idLeague);
    //List<TeamResponse> getMatchStatisticsByTeam(long idLeague);
}
