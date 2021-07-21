package com.project.football.services;

import com.project.football.item.TeamClassificationItem;
import com.project.football.model.Team;
import com.project.football.repository.GameMatchRepository;
import com.project.football.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsReportService implements IStatisticReport{

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private GameMatchRepository gameMatchRepository;

    @Override
    public List<TeamClassificationItem> getGoalsByMatchByLeague(long idLeague) {
        List<Team> teamList = teamRepository.findAll();
        List<TeamClassificationItem>  itemList= new ArrayList<>();

        if(!teamList.isEmpty()){
            for(Team team: teamList){
                int wonL = gameMatchRepository.localWonMatchesByLeague(idLeague, team.getIdTeam()).orElse(0);
                int wonV = gameMatchRepository.visitorWonMatchesByLeague(idLeague, team.getIdTeam()).orElse(0);
                int lostL = gameMatchRepository.localLostMatchesByLeague(idLeague, team.getIdTeam()).orElse(0);
                int lostV = gameMatchRepository.visitorLostMatchesByLeague(idLeague, team.getIdTeam()).orElse(0);
                int tiedL = gameMatchRepository.localTieMatchesByLeague(idLeague, team.getIdTeam()).orElse(0);
                int tiedV = gameMatchRepository.visitorTieMatchesByLeague(idLeague, team.getIdTeam()).orElse(0);
                itemList.add(
                        new TeamClassificationItem(
                                team.getIdTeam(),
                                team.getName(),
                                team.getTrainer(),
                                team.getImage(),
                                wonL + wonV,
                                lostL + lostV,
                                tiedL + tiedV
                                ));
            }
        }
        return itemList;
    }
}
