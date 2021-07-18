package com.project.football.item;

import com.project.football.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArbiterItem implements IItem{
    private long idArbiter;
    private String arbiter;
    private String city;
    private String country;
    private long idCity;
}
