package com.example.planetas.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Planeta")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanetaDocument {

    @Id
    private String id;
    private String nome;
    private String terreno;
    private String clima;
}
