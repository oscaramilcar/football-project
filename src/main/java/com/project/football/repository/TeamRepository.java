package com.project.football.repository;

import com.project.football.model.Team;
import com.project.football.response.TeamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    /*@Procedure(value="getStatisticsByTeam(:idL)")
    Object[] getMatchStatisticsByTeam(@Param("idL") long idLeague);
    List<TeamResponse> getMatchStatisticsByTeam(@Param("idL") long idLeague);*/
}
