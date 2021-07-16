package com.project.football.controller;

import com.project.football.model.City;
import com.project.football.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("/cities")
public class CityController {

    /*@Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> getAll(){
        return cityRepository.findAll();
    }*/
}
