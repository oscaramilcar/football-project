package com.project.football.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StadiumRequest {
    private long idStadium;
    private String name;
    private long idCity;
}
