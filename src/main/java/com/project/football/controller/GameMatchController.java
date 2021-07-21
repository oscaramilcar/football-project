package com.project.football.controller;

import com.project.football.item.GameMatchItem;
import com.project.football.item.GolsByGameMatchItem;
import com.project.football.item.IItem;
import com.project.football.model.GameMatch;
import com.project.football.repository.*;
import com.project.football.request.GameMatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class GameMatchController {

    @Autowired
    private LeagueRepository leagueRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private StadiumRepository stadiumRepository;
    @Autowired
    private ArbiterRepository arbiterRepository;
    @Autowired
    private GameMatchRepository gameMatchRepository;


    private IItem getItem(GameMatch gameMatch){
        return new GameMatchItem(
                gameMatch.getIdGameMatch(),
                gameMatch.getLeague().getName(),
                gameMatch.getArbiter().getName(),
                gameMatch.getLocalTeam().getName(),
                gameMatch.getVisitorTeam().getName(),
                gameMatch.getStadium().getName(),
                gameMatch.getDate()+"",
                gameMatch.getLocalScore(),
                gameMatch.getVisitorScore(),
                gameMatch.getLeague().getIdLeague(),
                gameMatch.getArbiter().getIdArbiter(),
                gameMatch.getLocalTeam().getIdTeam(),
                gameMatch.getVisitorTeam().getIdTeam(),
                gameMatch.getStadium().getIdStadium());
    }

    @GetMapping
    public List<IItem> getAll(){
        List<GameMatch> gameMatchList = gameMatchRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!gameMatchList.isEmpty()){
            for(GameMatch gameMatch: gameMatchList){
                itemList.add(getItem(gameMatch));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var gameMatch= gameMatchRepository.findById(id).orElse(null);
        IItem item = new GameMatchItem();

        if(gameMatch!=null){
            item = getItem(gameMatch);
        }
        return item;
    }

    @PostMapping
    public IItem create(@RequestBody GameMatchRequest gameMatchTMP){
        var league = leagueRepository.findById(gameMatchTMP.getIdLeague()).orElse(null);
        var arbiter = arbiterRepository.findById(gameMatchTMP.getIdArbiter()).orElse(null);
        var stadium = stadiumRepository.findById(gameMatchTMP.getIdStadium()).orElse(null);
        var localTeam = teamRepository.findById(gameMatchTMP.getIdLocalTeam()).orElse(null);
        var visitorTeam = teamRepository.findById(gameMatchTMP.getIdVisitorTeam()).orElse(null);
        var gameMatch = new GameMatch(0, league, arbiter, localTeam, visitorTeam, stadium,gameMatchTMP.getDate(),0,0);
        gameMatch = gameMatchRepository.save(gameMatch);

        return getItem(gameMatch);
    }

    @PutMapping
    public IItem update(@RequestBody GameMatchRequest gameMatchTMP){
        var gameMatch = gameMatchRepository.findById(gameMatchTMP.getIdGameMatch()).orElse(null);
        var arbiter = arbiterRepository.findById(gameMatchTMP.getIdArbiter()).orElse(null);
        var stadium = stadiumRepository.findById(gameMatchTMP.getIdStadium()).orElse(null);
        var localTeam = teamRepository.findById(gameMatchTMP.getIdLocalTeam()).orElse(null);
        var visitorTeam = teamRepository.findById(gameMatchTMP.getIdVisitorTeam()).orElse(null);
        var league = leagueRepository.findById(gameMatchTMP.getIdLeague()).orElse(null);
        gameMatch.setArbiter(arbiter);
        gameMatch.setLeague(league);
        gameMatch.setStadium(stadium);
        gameMatch.setLocalTeam(localTeam);
        gameMatch.setVisitorTeam(visitorTeam);
        gameMatch.setDate(gameMatchTMP.getDate());
        gameMatch = gameMatchRepository.save(gameMatch);

        return getItem(gameMatch);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        gameMatchRepository.deleteById(id);
    }

    @GetMapping("/team/{id}")
    public List<IItem> getAllByTeam(@PathVariable("id") long id){
        List<GameMatch> gameMatchList = gameMatchRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!gameMatchList.isEmpty()){
            for(GameMatch gameMatch: gameMatchList){
                if(gameMatch.getLocalTeam().getIdTeam()==id || gameMatch.getVisitorTeam().getIdTeam()==id){
                    itemList.add(getItem(gameMatch));
                }
            }
        }

        return itemList;
    }

    @GetMapping("/league/{id}")
    public List<IItem> getAllByLeague(@PathVariable("id") long id){
        List<GameMatch> gameMatchList = gameMatchRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!gameMatchList.isEmpty()){
            for(GameMatch gameMatch: gameMatchList){
                if(gameMatch.getLeague().getIdLeague()==id){
                    itemList.add(getItem(gameMatch));
                }
            }
        }

        return itemList;
    }
    @GetMapping("/league/games/{idL}")
    public List<GolsByGameMatchItem> getGoalsByMatch(@PathVariable("idL") long idL){
        return gameMatchRepository.goalsByMatch(idL);
    }
}
