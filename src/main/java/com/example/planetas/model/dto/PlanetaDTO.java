package com.example.planetas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanetaDTO implements Serializable {

    private String id;
    private String nome;
    private String terreno;
    private String clima;
    private Long quantidadeAparicoes;
}
