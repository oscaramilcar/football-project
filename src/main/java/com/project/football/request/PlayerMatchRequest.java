package com.project.football.request;

import com.project.football.model.GameMatch;
import com.project.football.model.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerMatchRequest {
    private long idPlayerMatch;
    private long idPlayer;
    private int goals;
    private long idGameMatch;
}
