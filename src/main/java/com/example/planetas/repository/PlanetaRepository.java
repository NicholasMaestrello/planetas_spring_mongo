package com.example.planetas.repository;

import com.example.planetas.model.document.PlanetaDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetaRepository extends ReactiveMongoRepository<PlanetaDocument, String> {
}
