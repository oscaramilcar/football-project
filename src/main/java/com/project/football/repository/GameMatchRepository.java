package com.project.football.repository;

import com.project.football.model.GameMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameMatchRepository extends JpaRepository<GameMatch, Long> {

    @Query(value = "SELECT SUM(pm.goals) FROM PlayerMatch pm INNER JOIN pm.player p INNER JOIN p.team t " +
                    "INNER JOIN pm.gameMatch g ON g.visitorTeam.idTeam = t.idTeam WHERE g.idGameMatch = ?1")
    Optional<Integer> sumGoalsVisitorTeam(long idGame);

    @Query(value = "SELECT SUM(pm.goals) FROM PlayerMatch pm INNER JOIN pm.player p INNER JOIN p.team t " +
                    "INNER JOIN pm.gameMatch g ON g.localTeam.idTeam = t.idTeam WHERE g.idGameMatch = ?1")
    Optional<Integer> sumGoalsLocalTeam(long idGame);
}
