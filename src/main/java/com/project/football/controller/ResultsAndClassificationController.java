package com.project.football.controller;

import com.project.football.item.TeamClassificationItem;
import com.project.football.services.StatisticsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class ResultsAndClassificationController {

    @Autowired
    private StatisticsReportService statisticsReportService;

    @GetMapping("/team/matchesByLeague/{id}")
    public List<TeamClassificationItem> getResultsByLeague(@PathVariable("id") long id){
       return statisticsReportService.getGoalsByMatchByLeague(id);
    }
}
