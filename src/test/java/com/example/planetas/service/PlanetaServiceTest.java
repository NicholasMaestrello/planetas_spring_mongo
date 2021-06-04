package com.example.planetas.service;

import com.example.planetas.integration.swapi.service.SwApiService;
import com.example.planetas.model.document.PlanetaDocument;
import com.example.planetas.model.dto.PlanetaDTO;
import com.example.planetas.model.mapper.MapStruct;
import com.example.planetas.repository.PlanetaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class PlanetaServiceTest {
    @InjectMocks
    private PlanetaService planetaService;

    @Mock
    private PlanetaRepository planetaRepository;

    @Mock
    private MapStruct mapStruct;

    @Mock
    private SwApiService swApiService;

    @DisplayName("Listar todos os planetas")
    @Test
    public void test1() {
        var quantidadeAparicoes = 2L;
        var mockPlaneta01 = PlanetaDocument.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build();
        List<PlanetaDocument> mockPlanetas = Arrays.asList(mockPlaneta01);

        Mockito.when(planetaRepository.findAll(Mockito.any(Example.class)))
                .thenReturn(Flux.fromIterable(mockPlanetas));
        Mockito.when(mapStruct.planetaDocumentToPlanetaDto(Mockito.any()))
                .thenReturn(PlanetaDTO.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build());
        Mockito.when(swApiService.getQuantidadeFilmes(Mockito.any())).thenReturn(quantidadeAparicoes);


        StepVerifier.create(planetaService.getAllPlanetas(""))
                .expectSubscription()
                .expectNextMatches(item -> isValidPlaneta(mockPlaneta01, item, quantidadeAparicoes))
                .verifyComplete();
    }

    @DisplayName("Buscar 1 planeta por id")
    @Test
    public void test2() {
        var quantidadeAparicoes = 2L;
        var mockPlaneta01 = PlanetaDocument.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build();

        Mockito.when(planetaRepository.findById(Mockito.any(String.class)))
                .thenReturn(Mono.just(mockPlaneta01));
        Mockito.when(mapStruct.planetaDocumentToPlanetaDto(Mockito.any()))
                .thenReturn(PlanetaDTO.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build());
        Mockito.when(swApiService.getQuantidadeFilmes(Mockito.any())).thenReturn(quantidadeAparicoes);


        StepVerifier.create(planetaService.findOnePlaneta(""))
                .expectSubscription()
                .expectNextMatches(item -> isValidPlaneta(mockPlaneta01, item, quantidadeAparicoes))
                .verifyComplete();
    }

    @DisplayName("Criar um planeta")
    @Test
    public void test3() {
        var quantidadeAparicoes = 2L;
        var mockPlanetaPayload = PlanetaDTO.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build();
        var mockPlaneta01 = PlanetaDocument.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build();

        var mapStructPlanetaDTO = PlanetaDTO.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build();
        var mapStructPlanetaDocument = PlanetaDocument.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build();

        Mockito.when(planetaRepository.save(Mockito.any())).thenReturn(Mono.just(mockPlaneta01));
        Mockito.when(mapStruct.planetaDocumentToPlanetaDto(Mockito.any())).thenReturn(mapStructPlanetaDTO);
        Mockito.when(mapStruct.planetaDtoToPlanetaDocument(Mockito.any())).thenReturn(mapStructPlanetaDocument);
        Mockito.when(swApiService.getQuantidadeFilmes(Mockito.any())).thenReturn(quantidadeAparicoes);


        StepVerifier.create(planetaService.createPlaneta(mockPlanetaPayload))
                .expectSubscription()
                .expectNextMatches(item -> isValidPlaneta(mockPlaneta01, item, quantidadeAparicoes))
                .verifyComplete();
    }

    @DisplayName("Editar um planeta")
    @Test
    public void test4() {
        var quantidadeAparicoes = 2L;
        var mockPlanetaPayload = PlanetaDTO.builder().id("0123").nome("Planeta 02").clima("Clima 02").terreno("Terreno 02").build();
        var mockPlaneta01 = PlanetaDocument.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build();
        var mockPlaneta02  = PlanetaDocument.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build();

        var mapStructPlanetaDTO = PlanetaDTO.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build();
        var mapStructPlanetaDocument = PlanetaDocument.builder().id("0123").nome("Planeta 01").clima("Clima 01").terreno("Terreno 01").build();

        Mockito.when(planetaRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(mockPlaneta01));
        Mockito.when(planetaRepository.save(Mockito.any())).thenReturn(Mono.just(mockPlaneta02));
        Mockito.when(mapStruct.planetaDocumentToPlanetaDto(Mockito.any())).thenReturn(mapStructPlanetaDTO);
        Mockito.when(mapStruct.planetaDtoToPlanetaDocument(Mockito.any())).thenReturn(mapStructPlanetaDocument);
        Mockito.when(swApiService.getQuantidadeFilmes(Mockito.any())).thenReturn(quantidadeAparicoes);


        StepVerifier.create(planetaService.updatePlaneta("0123", mockPlanetaPayload))
                .expectSubscription()
                .expectNextMatches(item -> isValidPlaneta(mockPlaneta02, item, quantidadeAparicoes))
                .verifyComplete();
    }

    @DisplayName("Deletar um planeta")
    @Test
    public void test5() {
        Mockito.when(planetaRepository.deleteById(Mockito.any(String.class))).thenReturn(Mono.empty());

        StepVerifier.create(planetaService.deletePlaneta("0123"))
                .expectSubscription()
                .verifyComplete();
    }

    private boolean isValidPlaneta(PlanetaDocument planetaDocument, PlanetaDTO item, Long quantidadeAparicoes) {
        return item.getId().equals(planetaDocument.getId())
                && item.getClima().equals(planetaDocument.getClima())
                && item.getNome().equals(planetaDocument.getNome())
                && item.getTerreno().equals(planetaDocument.getTerreno())
                && item.getQuantidadeAparicoes().equals(quantidadeAparicoes);
    }
}
