package com.project.football.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArbiterRequest {
    private long idArbiter;
    private String name;
    private long idCity;
}
