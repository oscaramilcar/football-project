package com.project.football.controller;

import com.project.football.item.IItem;
import com.project.football.item.PositionItem;
import com.project.football.item.TeamItem;
import com.project.football.model.Position;
import com.project.football.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    private IItem getItem(Position position){
        return new PositionItem(
                position.getIdPosition(),
                position.getName());
    }

    @GetMapping
    public List<IItem> getAll(){
        List<Position> positionList = positionRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!positionList.isEmpty()){
            for(Position position: positionList){
                itemList.add(getItem(position));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var position= positionRepository.findById(id).orElse(null);
        IItem item = new TeamItem();

        if(position!=null){
            item = getItem(position);
        }
        return item;
    }

    @PostMapping
    public IItem create(@RequestBody PositionItem positionTMP){
        var position = new Position(0, positionTMP.getName());
        position = positionRepository.save(position);

        return getItem(position);
    }

    @PutMapping
    public IItem update(@RequestBody PositionItem positionTMP){
        var position = positionRepository.findById(positionTMP.getIdPosition()).orElse(null);
        position.setName(positionTMP.getName());
        position = positionRepository.save(position);

        return getItem(position);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        positionRepository.deleteById(id);
    }
}
