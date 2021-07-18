package com.project.football.services;

import com.project.football.model.League;
import com.project.football.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueServiceImpl implements ILeagueService{

    @Autowired
    private LeagueRepository leagueRepository;


    @Override
    public List<League> findAll() {
        return leagueRepository.findAll();
    }

    @Override
    public Optional<League> findById(Long id) {
        return leagueRepository.findById(id);
    }

    @Override
    public League save(League league) {
        return leagueRepository.save(league);
    }

    @Override
    public void delete(League league) {
        leagueRepository.delete(league);
    }
}
