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
    private final WebClient pythonClient=WebClient.create("http://localhost:8000");

    public JsonNode getRecommendations(RecommendationRequestDto dto){
        return pythonClient.post()
                .uri("/recommend")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }

}