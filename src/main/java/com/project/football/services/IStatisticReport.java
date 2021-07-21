package com.project.football.services;

import com.project.football.item.TeamClassificationItem;

import java.util.List;

public interface IStatisticReport {
    List<TeamClassificationItem> getGoalsByMatchByLeague(long idLeague);
}
