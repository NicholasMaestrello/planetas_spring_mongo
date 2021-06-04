package com.example.planetas.controller;

import com.example.planetas.model.dto.PlanetaDTO;
import com.example.planetas.service.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/planeta")
public class PlanetaController {

    @Autowired
    private PlanetaService planetaService;

    @GetMapping()
    private Flux<PlanetaDTO> getAllPlanetas(String nome) {
        return planetaService.getAllPlanetas(nome);
    }

    @GetMapping("/{id}")
    private Mono<PlanetaDTO> findOnePlaneta(@PathVariable String id) {
        return planetaService.findOnePlaneta(id);
    }

    @PostMapping()
    private Mono<PlanetaDTO> createPlaneta(@RequestBody PlanetaDTO planeta) {
        return planetaService.createPlaneta(planeta);
    }

    @PutMapping("/{id}")
    private Mono<PlanetaDTO> updatePlaneta(@PathVariable String id, @RequestBody PlanetaDTO planeta) {
        return planetaService.updatePlaneta(id, planeta);
    }

    @DeleteMapping("/{id}")
    private Mono<Void> deletePlaneta(@PathVariable String id) {
        return planetaService.deletePlaneta(id);
    }
}
