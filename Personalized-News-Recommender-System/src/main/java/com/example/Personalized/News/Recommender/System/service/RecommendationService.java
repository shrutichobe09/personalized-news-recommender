package com.example.Personalized.News.Recommender.System.service;

import com.example.Personalized.News.Recommender.System.dto.RecommendationRequestDto;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final WebClient pythonClient= WebClient.create("http://fastapi-ml:8000");

   public JsonNode getRecommendations(RecommendationRequestDto dto){
    try {
        System.out.println("Sending request to FastAPI service: " + dto);
        JsonNode response = pythonClient.post()
                .uri("/recommend")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        System.out.println("Response from FastAPI: " + response);
        return response;

    } catch (Exception e) {
        System.err.println("Error calling FastAPI service: " + e.getMessage());
        e.printStackTrace();  // Full stack trace
        throw e;
    }
}
}