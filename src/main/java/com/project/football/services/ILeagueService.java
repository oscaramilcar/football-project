package com.project.football.services;

import com.project.football.model.Country;
import com.project.football.model.League;

import java.util.List;
import java.util.Optional;

public interface ILeagueService {
    List<League> findAll();
    Optional<League> findById(Long id);
    League save(League league);
    void delete(League league);
}
