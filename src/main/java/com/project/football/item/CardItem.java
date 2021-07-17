package com.project.football.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardItem implements IItem{
    private long idCard;
    private String color;
    private int quantity;
    private long playerMatch;
    private String player;
    private String date;
    private String league;
}
