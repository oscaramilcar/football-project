package com.project.football.repository;

import com.project.football.model.PlayerMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {
}
