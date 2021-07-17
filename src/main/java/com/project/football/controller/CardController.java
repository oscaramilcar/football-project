package com.project.football.controller;

import com.project.football.item.ArbiterItem;
import com.project.football.item.CardItem;
import com.project.football.item.IItem;
import com.project.football.model.Card;
import com.project.football.repository.CardRepository;
import com.project.football.repository.PlayerMatchRepository;
import com.project.football.request.CardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private PlayerMatchRepository playerMatchRepository;

    private IItem getItem(Card card){
        return new CardItem(
                card.getIdCard(),
                card.getColor(),
                card.getQuantity(),
                card.getPlayerMatch().getIdPlayerMatch(),
                card.getPlayerMatch().getPlayer().getName(),
                card.getPlayerMatch().getGameMatch().getDate()+"",
                card.getPlayerMatch().getGameMatch().getLeague().getName());
    }

    @GetMapping
    public List<IItem> getAll(){
        List<Card> cardList = cardRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!cardList.isEmpty()){
            for(Card card: cardList){
                itemList.add(getItem(card));
            }
        }

        return itemList;
    }

    @GetMapping("/{id}")
    public IItem findById(@PathVariable("id") long id){
        var card= cardRepository.findById(id).orElse(null);
        IItem item = new ArbiterItem();

        if(card!=null){
            item = getItem(card);
        }
        return item;
    }

    @PostMapping
    public IItem create(@RequestBody CardRequest cardTMP){
        var playerMatch = playerMatchRepository.findById(cardTMP.getIdPlayerMatch()).orElse(null);
        var card = new Card(0, cardTMP.getColor(), cardTMP.getQuantity(), playerMatch);
        card = cardRepository.save(card);

        return getItem(card);
    }

    @PutMapping
    public IItem update(@RequestBody CardRequest cardTMP){
        var card = cardRepository.findById(cardTMP.getIdCard()).orElse(null);
        var playerMatch = playerMatchRepository.findById(cardTMP.getIdPlayerMatch()).orElse(null);
        card.setPlayerMatch(playerMatch);
        card.setColor(cardTMP.getColor());
        card.setQuantity(cardTMP.getQuantity());
        card = cardRepository.save(card);

        return getItem(card);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        cardRepository.deleteById(id);
    }

    @GetMapping("/player/{id}")
    public List<IItem> getAllByPlayer(@PathVariable("id") long id){
        List<Card> cardList = cardRepository.findAll();
        List<IItem> itemList = new ArrayList<>();

        if(!cardList.isEmpty()){
            for(Card card: cardList){
                if(card.getPlayerMatch().getPlayer().getIdPlayer()==id){
                    itemList.add(getItem(card));
                }
            }
        }

        return itemList;
    }
}
