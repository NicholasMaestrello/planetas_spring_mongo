package com.example.planetas.integration.swapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SwapiResponseDTO implements Serializable {

    private Long count;
    private String next;
    private String previous;
    private List<SwapiPlanetaDTO> results;
}
