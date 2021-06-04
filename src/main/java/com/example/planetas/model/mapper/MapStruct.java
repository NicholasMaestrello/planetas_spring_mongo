package com.example.planetas.model.mapper;

import com.example.planetas.model.document.PlanetaDocument;
import com.example.planetas.model.dto.PlanetaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStruct {

    PlanetaDTO planetaDocumentToPlanetaDto(PlanetaDocument planeta);

    PlanetaDocument planetaDtoToPlanetaDocument(PlanetaDTO planeta);
}
