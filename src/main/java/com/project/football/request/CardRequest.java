package com.project.football.request;

import com.project.football.model.PlayerMatch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {
    private long idCard;
    private String color;
    private int quantity;
    private long idPlayerMatch;
}
