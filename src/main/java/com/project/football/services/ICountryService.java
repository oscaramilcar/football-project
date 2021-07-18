package com.project.football.services;

import com.project.football.model.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService {
    List<Country> findAll();
    Optional<Country> findById(String id);
}
