package com.project.football;

import com.project.football.model.League;
import com.project.football.repository.LeagueRepository;
import com.project.football.services.ILeagueService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLeagueRepository {

    @Autowired
    private ILeagueService service;

    @MockBean
    private LeagueRepository repository;

    @Test
    public void shoulFindAllLeagues() {
        Mockito.when(repository.findAll()).thenReturn(Stream
                .of(new League(1, "LaLiga",2021), new League(2, "Serie A", 2021)).collect(Collectors.toList()));
        Assert.assertEquals(2, service.findAll().size());
    }

    @Test
    public void shouldsaveLeagueTest() {
        League league = new League(2,"Premier League",2021);
        Mockito.when(repository.save(league)).thenReturn(league);
        Assert.assertEquals(league, service.save(league));
    }

    @Test
    public void deleteUserTest() {
        League league = new League(3, "Bundesliga", 2021);
        service.delete(league);
        Mockito.verify(repository, Mockito.times(1)).delete(league);
    }
}
