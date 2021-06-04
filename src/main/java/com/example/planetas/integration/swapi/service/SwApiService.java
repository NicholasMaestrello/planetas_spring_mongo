package com.example.planetas.integration.swapi.service;

import com.example.planetas.integration.swapi.model.dto.SwapiResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
@Slf4j
public class SwApiService {

    private static final String swapiUrl = "https://swapi.dev/api/planets/?search=";

    //    private final WebClient client = WebClient.create("https://swapi.dev/api/planets/");
    private final WebClient client = WebClient.create("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");


    public Long getQuantidadeFilmes(@NonNull String nome) {
        var planeta = getPlaneta(nome);
        if (planeta != null && planeta.getResults().size() == 1) {
            return Long.valueOf(planeta.getResults().get(0).getFilms().size());
        }
        return 0L;
    }

    private SwapiResponseDTO getPlaneta(String nomePlaneta) {

        var mapper = new ObjectMapper();

        try (var client = HttpClients.createDefault()) {


            var request = new HttpGet(swapiUrl + nomePlaneta.replace(" ", "%20"));

            var response = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), SwapiResponseDTO.class));

            return response;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }


}
