package com.project.football.controller;

import com.project.football.item.CityItem;
import com.project.football.item.IItem;
import com.project.football.model.City;
import com.project.football.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    private IItem getItem(City city){
        return new CityItem(
                city.getIdCity(),
                city.getName(),
                city.getCountry().getIdCountry());
    }

    @GetMapping
    public List<IItem> getAll(){
        List<City> cityList = cityRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!cityList.isEmpty()){
            for(City city: cityList){
                itemList.add(getItem(city));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var city= cityRepository.findById(id).orElse(null);
        IItem item = new CityItem();

        if(city!=null){
            item = getItem(city);
        }
        return item;
    }
}
