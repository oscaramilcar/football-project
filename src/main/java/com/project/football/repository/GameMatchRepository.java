package com.project.football.repository;

import com.project.football.model.GameMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameMatchRepository extends JpaRepository<GameMatch, Long> {
}
