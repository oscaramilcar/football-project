package com.project.football.controller;

import com.project.football.item.IItem;
import com.project.football.item.TeamItem;
import com.project.football.model.Team;
import com.project.football.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clubs")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    private IItem getItem(Team team){
        return new TeamItem(
                team.getIdTeam(),
                team.getName(),
                team.getTrainer(),
                team.getImage());
    }

    @GetMapping
    public List<IItem> getAll(){
        List<Team> teamList = teamRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!teamList.isEmpty()){
            for(Team team: teamList){
                itemList.add(getItem(team));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var playerMatch= teamRepository.findById(id).orElse(null);
        IItem item = new TeamItem();

        if(playerMatch!=null){
            item = getItem(playerMatch);
        }
        return item;
    }

    @PostMapping
    public IItem create(@RequestBody TeamItem teamMTP){
        var team = new Team(0, teamMTP.getName(), teamMTP.getTrainer(), teamMTP.getImage());
        team = teamRepository.save(team);

        return getItem(team);
    }

    @PutMapping
    public IItem update(@RequestBody TeamItem teamMTP){
        var team = teamRepository.findById(teamMTP.getIdTeam()).orElse(null);
        team.setName(teamMTP.getName());
        team.setTrainer(teamMTP.getTrainer());
        team.setImage(teamMTP.getImage());
        team = teamRepository.save(team);

        return getItem(team);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        teamRepository.deleteById(id);
    }
}
