package com.example.Personalized.News.Recommender.System.service;

import com.example.Personalized.News.Recommender.System.dto.ArticleDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

    @Value("${gnews.api-key}")
    private String apiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ArticleDTO> fetchNews(String category) {
        List<ArticleDTO> articles = new ArrayList<>();
        try {
            String apiUrl = "https://gnews.io/api/v4/top-headlines?topic=" + category + "&lang=en&token=" + apiKey;

            WebClient webClient = WebClient.builder().build();

            JsonNode response = webClient.get()
                    .uri(apiUrl)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .onErrorResume(error -> {
                        log.error("Error fetching news from GNews API: {}", error.getMessage());
                        return Mono.empty();
                    })
                    .block();

            if (response != null && response.has("articles")) {
                for (JsonNode node : response.get("articles")) {
                    ArticleDTO dto = new ArticleDTO();
                    dto.setTitle(node.get("title").asText());
                    dto.setDescription(node.get("description").asText());
                    dto.setUrl(node.get("url").asText());
                    dto.setCategory(category);
                    articles.add(dto);
                }
            } else {
                log.warn("No articles found or invalid response from GNews for category: {}", category);
            }

        } catch (Exception e) {
            log.error("Exception in fetchNews(): {}", e.getMessage(), e);
        }

        return articles;
    }
}
