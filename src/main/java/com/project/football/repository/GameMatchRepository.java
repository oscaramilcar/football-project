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

    @Query(value = "SELECT COUNT(g.visitorScore) FROM GameMatch g where g.visitorScore=3 AND g.visitorTeam.idTeam=?2 AND g.league.idLeague=?1")
    Optional<Integer> visitorWonMatchesByLeague(long idLeague, long idTeam);
    @Query(value = "SELECT COUNT(g.localScore) FROM GameMatch g where g.localScore=3 AND g.localTeam.idTeam=?2 AND g.league.idLeague=?1")
    Optional<Integer> localWonMatchesByLeague(long idLeague, long idTeam);

    @Query(value = "SELECT COUNT(g.visitorScore) FROM GameMatch g where g.visitorScore=0 AND g.visitorTeam.idTeam=?2 AND g.league.idLeague=?1")
    Optional<Integer> visitorLostMatchesByLeague(long idLeague, long idTeam);
    @Query(value = "SELECT COUNT(g.localScore) FROM GameMatch g where g.localScore=0 AND g.localTeam.idTeam=?2 AND g.league.idLeague=?1")
    Optional<Integer> localLostMatchesByLeague(long idLeague, long idTeam);

    @Query(value = "SELECT COUNT(g.visitorScore) FROM GameMatch g where g.visitorScore=1 AND g.visitorTeam.idTeam=?2 AND g.league.idLeague=?1")
    Optional<Integer> visitorTieMatchesByLeague(long idLeague, long idTeam);
    @Query(value = "SELECT COUNT(g.localScore) FROM GameMatch g where g.localScore=1 AND g.localTeam.idTeam=?2 AND g.league.idLeague=?1")
    Optional<Integer> localTieMatchesByLeague(long idLeague, long idTeam);

}
