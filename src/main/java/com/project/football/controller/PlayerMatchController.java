package com.project.football.controller;

import com.project.football.item.ArbiterItem;
import com.project.football.item.IItem;
import com.project.football.item.PlayerMatchItem;
import com.project.football.model.PlayerMatch;
import com.project.football.repository.GameMatchRepository;
import com.project.football.repository.PlayerMatchRepository;
import com.project.football.repository.PlayerRepository;
import com.project.football.request.PlayerMatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/statisticsByMatch")
public class PlayerMatchController {

    @Autowired
    private PlayerMatchRepository playerMatchRepository;
    @Autowired
    private GameMatchRepository gameMatchRepository;
    @Autowired
    private PlayerRepository playerRepository;

    private IItem getItem(PlayerMatch playerMatch){
        return new PlayerMatchItem(
                playerMatch.getIdPlayerMatch(),
                playerMatch.getPlayer().getName()+" "+playerMatch.getPlayer().getLastName(),
                playerMatch.getGoals(),
                playerMatch.getGameMatch().getLeague().getName(),
                playerMatch.getGameMatch().getDate()+"",
                playerMatch.getPlayer().getTeam().getName(),
                playerMatch.getGameMatch().getIdGameMatch(),
                playerMatch.getPlayer().getIdPlayer());
    }

    private IItem getItemAndUpdateScores(PlayerMatch playerMatch){
        var gameMatch = gameMatchRepository.findById(playerMatch.getGameMatch().getIdGameMatch()).orElse(null);
        int localGoals= gameMatchRepository.sumGoalsLocalTeam(playerMatch.getGameMatch().getIdGameMatch()).orElse(0);
        int visitorGoals= gameMatchRepository.sumGoalsVisitorTeam(playerMatch.getGameMatch().getIdGameMatch()).orElse(0);

        if(gameMatch!=null){

            gameMatch.setLocalScore(localGoals);
            gameMatch.setVisitorScore(visitorGoals);
            gameMatchRepository.save(gameMatch);
        }
        return getItem(playerMatch);
    }

    @GetMapping
    public List<IItem> getAll(){
        List<PlayerMatch> playerMatchList = playerMatchRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!playerMatchList.isEmpty()){
            for(PlayerMatch playerMatch: playerMatchList){
                itemList.add(getItem(playerMatch));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var playerMatch= playerMatchRepository.findById(id).orElse(null);
        IItem item = new ArbiterItem();

        if(playerMatch!=null){
            item = getItem(playerMatch);
        }
        return item;
    }

    @PostMapping
    public IItem create(@RequestBody PlayerMatchRequest playerMatchMTP){
        var player = playerRepository.findById(playerMatchMTP.getIdPlayer()).orElse(null);
        var gameMatch = gameMatchRepository.findById(playerMatchMTP.getIdGameMatch()).orElse(null);
        var playerMatch = new PlayerMatch(0, player, playerMatchMTP.getGoals(), gameMatch);
        playerMatch = playerMatchRepository.save(playerMatch);

        return getItemAndUpdateScores(playerMatch);
    }

    @PutMapping
    public IItem update(@RequestBody PlayerMatchRequest playerMatchMTP){
        var playerMatch = playerMatchRepository.findById(playerMatchMTP.getIdPlayerMatch()).orElse(null);
        var player = playerRepository.findById(playerMatchMTP.getIdPlayer()).orElse(null);
        var gameMatch = gameMatchRepository.findById(playerMatchMTP.getIdGameMatch()).orElse(null);
        playerMatch.setPlayer(player);
        playerMatch.setGoals(playerMatchMTP.getGoals());
        playerMatch.setGameMatch(gameMatch);
        playerMatch = playerMatchRepository.save(playerMatch);

        return getItemAndUpdateScores(playerMatch);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        var playerMatch = playerMatchRepository.findById(id).orElse(null);
        playerMatchRepository.deleteById(id);
        getItemAndUpdateScores(playerMatch);
    }

    @GetMapping("/team/{id}")
    public List<IItem> getAllByTeam(@PathVariable("id") long id){
        List<PlayerMatch> playerMatchList = playerMatchRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!playerMatchList.isEmpty()){
            for(PlayerMatch playerMatch: playerMatchList){
                if(playerMatch.getPlayer().getTeam().getIdTeam()==id){
                    itemList.add(getItem(playerMatch));
                }
            }
        }

        return itemList;
    }

    @GetMapping("/match/{id}")
    public List<IItem> getAllByMatch(@PathVariable("id") long id){
        List<PlayerMatch> playerMatchList = playerMatchRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!playerMatchList.isEmpty()){
            for(PlayerMatch playerMatch: playerMatchList){
                if(playerMatch.getGameMatch().getIdGameMatch()==id){
                    itemList.add(getItem(playerMatch));
                }
            }
        }

        return itemList;
    }

    @GetMapping("/league/{id}")
    public List<IItem> getAllByLeague(@PathVariable("id") long id){
        List<PlayerMatch> playerMatchList = playerMatchRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!playerMatchList.isEmpty()){
            for(PlayerMatch playerMatch: playerMatchList){
                if(playerMatch.getGameMatch().getLeague().getIdLeague()==id){
                    itemList.add(getItem(playerMatch));
                }
            }
        }

        return itemList;
    }
}
