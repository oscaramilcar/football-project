package com.project.football.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityItem implements IItem{
    private long idCity;
    private String name;
    private String idCountry;
}
