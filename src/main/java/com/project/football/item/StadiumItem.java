package com.project.football.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StadiumItem implements IItem{
    private long idStadium;
    private String stadium;
    private String city;
    private String country;
    private long idCity;
}
