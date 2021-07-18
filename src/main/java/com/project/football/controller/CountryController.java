package com.project.football.controller;

import com.project.football.exeption.ResourceNotFoundException;
import com.project.football.model.Country;
import com.project.football.model.User;
import com.project.football.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @GetMapping("/countries")
    public List<Country> getAll(){
        return countryService.findAll();
    }

    @GetMapping("/countries/{id}")
    public Country findById(@PathVariable("id") String id){
        return countryService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("country not found with id :" + id));
    }
}
