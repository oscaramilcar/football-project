package com.project.football.controller;

import com.project.football.item.ArbiterItem;
import com.project.football.item.IItem;
import com.project.football.model.Arbiter;
import com.project.football.repository.ArbiterRepository;
import com.project.football.repository.CityRepository;
import com.project.football.request.ArbiterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/arbiters")
public class ArbiterController {

    @Autowired
    private ArbiterRepository arbiterRepository;
    @Autowired
    private CityRepository cityRepository;

    private IItem getItem(Arbiter arbiter){
        return new ArbiterItem(
                arbiter.getIdArbiter(),
                arbiter.getName(),
                arbiter.getCity().getName(),
                arbiter.getCity().getCountry().getName(),
                arbiter.getCity().getIdCity());
    }

    @GetMapping
    public List<IItem> getAll(){
        List<Arbiter> arbiterList = arbiterRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!arbiterList.isEmpty()){
            for(Arbiter arbiter: arbiterList){
                itemList.add(getItem(arbiter));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var arbiter= arbiterRepository.findById(id).orElse(null);
        IItem item = new ArbiterItem();

        if(arbiter!=null){
            item = getItem(arbiter);
        }
        return item;
    }

    @PostMapping
    public IItem create(@RequestBody ArbiterRequest arbiterTMP){
        var city = cityRepository.findById(arbiterTMP.getIdCity()).orElse(null);
        var arbiter = new Arbiter(0, arbiterTMP.getName(), city);
        arbiter = arbiterRepository.save(arbiter);

        return getItem(arbiter);
    }

    @PutMapping
    public IItem update(@RequestBody ArbiterRequest arbiterTMP){
        var arbiter = arbiterRepository.findById(arbiterTMP.getIdArbiter()).orElse(null);
        var city = cityRepository.findById(arbiterTMP.getIdCity()).orElse(null);
        arbiter.setCity(city);
        arbiter.setName(arbiterTMP.getName());
        arbiter = arbiterRepository.save(arbiter);

        return getItem(arbiter);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        arbiterRepository.deleteById(id);
    }
}
