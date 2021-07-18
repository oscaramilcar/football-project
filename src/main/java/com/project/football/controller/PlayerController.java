package com.project.football.controller;

import com.project.football.item.ArbiterItem;
import com.project.football.item.IItem;
import com.project.football.item.PlayerItem;
import com.project.football.model.Player;
import com.project.football.repository.*;
import com.project.football.request.PlayerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PositionRepository positionRepository;

    private IItem getItem(Player player){
        return new PlayerItem(
                player.getIdPlayer(),
                player.getName(),
                player.getLastName(),
                player.getAge(),
                player.getTeam().getName(),
                player.getPosition().getName(),
                player.getImage(),
                player.getTeam().getIdTeam(),
                player.getPosition().getIdPosition());
    }

    @GetMapping
    public List<IItem> getAll(){
        List<Player> playerList = playerRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!playerList.isEmpty()){
            for(Player player: playerList){
                itemList.add(getItem(player));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var player= playerRepository.findById(id).orElse(null);
        IItem item = new ArbiterItem();

        if(player!=null){
            item = getItem(player);
        }
        return item;
    }

    @PostMapping
    public IItem create(@RequestBody PlayerRequest playerTMP){
        var position = positionRepository.findById(playerTMP.getIdPosition()).orElse(null);
        var team = teamRepository.findById(playerTMP.getIdTeam()).orElse(null);
        var player = new Player(0, playerTMP.getFirstName(), playerTMP.getFirstName(), playerTMP.getAge(), team, position, playerTMP.getImage());
        player = playerRepository.save(player);

        return getItem(player);
    }

    @PutMapping
    public IItem update(@RequestBody PlayerRequest playerTMP){
        var player = playerRepository.findById(playerTMP.getIdPlayer()).orElse(null);
        var position = positionRepository.findById(playerTMP.getIdPosition()).orElse(null);
        var team = teamRepository.findById(playerTMP.getIdTeam()).orElse(null);
        player.setName(playerTMP.getFirstName());
        player.setLastName(playerTMP.getLastName());
        player.setAge(playerTMP.getAge());
        player.setTeam(team);
        player.setPosition(position);
        player.setImage(playerTMP.getImage());
        player = playerRepository.save(player);

        return getItem(player);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        playerRepository.deleteById(id);
    }

    @GetMapping("/team/{id}")
    public List<IItem> getAllByTeam(@PathVariable("id") long id){
        List<Player> playerList = playerRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!playerList.isEmpty()){
            for(Player player: playerList){
                if(player.getTeam().getIdTeam()==id){
                    itemList.add(getItem(player));
                }
            }
        }

        return itemList;
    }
}
