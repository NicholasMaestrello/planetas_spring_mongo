package com.example.planetas.service;

import com.example.planetas.integration.swapi.service.SwApiService;
import com.example.planetas.model.document.PlanetaDocument;
import com.example.planetas.model.dto.PlanetaDTO;
import com.example.planetas.model.mapper.MapStruct;
import com.example.planetas.repository.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlanetaService {

    @Autowired
    private PlanetaRepository planetaRepository;

    @Autowired
    private MapStruct mapStruct;

    @Autowired
    private SwApiService swApiService;

    public Flux<PlanetaDTO> getAllPlanetas(String nome) {
        Flux<PlanetaDocument> all = planetaRepository.findAll(Example.of(PlanetaDocument.builder().nome(nome).build()));
        Flux<PlanetaDTO> map = all.map(p -> convertPlanetaWithQuantidade(p));
        return map;
    }

    public Mono<PlanetaDTO> findOnePlaneta(String id) {
        var planetaDocument = planetaRepository.findById(id);
        return planetaDocument.map(p -> convertPlanetaWithQuantidade(p));
    }

    public Mono<PlanetaDTO> createPlaneta(PlanetaDTO planetaDTO) {
        var planetaDocument = mapStruct.planetaDtoToPlanetaDocument(planetaDTO);
        Mono<PlanetaDocument> save = planetaRepository.save(planetaDocument);
        return save.map(p -> convertPlanetaWithQuantidade(p));
    }

    public Mono<PlanetaDTO> updatePlaneta(String id, PlanetaDTO planetaDTO) {
        var planetaDocument = planetaRepository.findById(id);

        var planetaDocumentUpdated = planetaDocument.flatMap(p -> {
            p.setClima(planetaDTO.getClima());
            p.setNome(planetaDTO.getNome());
            p.setTerreno(planetaDTO.getTerreno());
            return planetaRepository.save(p);
        });

        return planetaDocumentUpdated.map(p -> convertPlanetaWithQuantidade(p));
    }

    private PlanetaDTO convertPlanetaWithQuantidade(PlanetaDocument p) {
        var planeta = mapStruct.planetaDocumentToPlanetaDto(p);
        planeta.setQuantidadeAparicoes(swApiService.getQuantidadeFilmes(p.getNome()));
        return planeta;
    }

    public Mono<Void> deletePlaneta(String id) {
        return planetaRepository.deleteById(id);
    }
}
