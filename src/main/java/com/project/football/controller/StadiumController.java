package com.project.football.controller;

import com.project.football.item.ArbiterItem;
import com.project.football.item.IItem;
import com.project.football.item.StadiumItem;
import com.project.football.model.Stadium;
import com.project.football.repository.CityRepository;
import com.project.football.repository.StadiumRepository;
import com.project.football.request.StadiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stadiums")
public class StadiumController {

    @Autowired
    private StadiumRepository stadiumRepository;
    @Autowired
    private CityRepository cityRepository;

    private IItem getItem(Stadium stadium){
        return new StadiumItem(
                stadium.getIdStadium(),
                stadium.getName(),
                stadium.getCity().getName(),
                stadium.getCity().getCountry().getName(),
                stadium.getCity().getIdCity());
    }

    @GetMapping
    public List<IItem> getAll(){
        List<Stadium> stadiumList = stadiumRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!stadiumList.isEmpty()){
            for(Stadium stadium: stadiumList){
                itemList.add(getItem(stadium));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var arbiter= stadiumRepository.findById(id).orElse(null);
        IItem item = new ArbiterItem();

        if(arbiter!=null){
            item = getItem(arbiter);
        }
        return item;
    }

    @PostMapping
    public IItem create(@RequestBody StadiumRequest stadiumTMP){
        var city = cityRepository.findById(stadiumTMP.getIdCity()).orElse(null);
        var stadium = new Stadium(0, stadiumTMP.getName(), city);
        stadium = stadiumRepository.save(stadium);

        return getItem(stadium);
    }

    @PutMapping
    public IItem update(@RequestBody StadiumRequest stadiumTMP){
        var stadium = stadiumRepository.findById(stadiumTMP.getIdStadium()).orElse(null);
        var city = cityRepository.findById(stadiumTMP.getIdCity()).orElse(null);
        stadium.setCity(city);
        stadium.setName(stadiumTMP.getName());
        stadium = stadiumRepository.save(stadium);

        return getItem(stadium);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        stadiumRepository.deleteById(id);
    }
}
