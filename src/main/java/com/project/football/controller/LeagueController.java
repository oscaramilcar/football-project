package com.project.football.controller;

import com.project.football.exeption.ResourceNotFoundException;
import com.project.football.model.League;
import com.project.football.services.ILeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LeagueController {

    @Autowired
    private ILeagueService leagueService;

    @GetMapping("/leagues")
    public List<League> getAll(){
        return leagueService.findAll();
    }

    @GetMapping("/leagues/{id}")
    public League findById(@PathVariable("id") Long id){
        return leagueService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("league not found with id :" + id));
    }

    @PostMapping("/leagues")
    public League create(@RequestBody League league){
        return leagueService.save(league);
    }

    @PutMapping("/leagues/{id}")
    public League update(@RequestBody League league, @PathVariable ("id") Long id){
        League existingLeague = leagueService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("league not found with id :" + id));

        existingLeague.setName(league.getName());
        existingLeague.setYear(league.getYear());
        return leagueService.save(existingLeague);
    }

    @DeleteMapping("/leagues/{id}")
    public ResponseEntity<League> delete(@PathVariable ("id") Long id){
        League existingLeague = leagueService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("league not found with id :" + id));
        leagueService.delete(existingLeague);
        return ResponseEntity.ok().build();
    }

}
