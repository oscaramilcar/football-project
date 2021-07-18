package com.project.football.repository;

import com.project.football.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, String> {
}
