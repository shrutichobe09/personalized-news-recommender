package com.example.Personalized.News.Recommender.System.controller;

import com.example.Personalized.News.Recommender.System.dto.ArticleClickRequest;
import com.example.Personalized.News.Recommender.System.dto.ArticleDTO;
import com.example.Personalized.News.Recommender.System.dto.RecommendationRequestDto;
import com.example.Personalized.News.Recommender.System.model.User;
import com.example.Personalized.News.Recommender.System.repository.UserRepository;
import com.example.Personalized.News.Recommender.System.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final RecommendationService recommendationService;
    private final UserService userService;
    private final UserInteractionService userInteractionService;
    UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RecommendationRequestDto dto) {
        User registeredUser = userService.registerUser(dto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<ArticleDTO>> fetchLiveNews(@RequestParam (defaultValue = "general")String category) {
        return ResponseEntity.ok(newsService.fetchNews(category));
    }

    @PostMapping("/recommend")
    public ResponseEntity<JsonNode> getRecommendations(@RequestBody RecommendationRequestDto dto) {
        return ResponseEntity.ok(recommendationService.getRecommendations(dto));
    }

    @PostMapping("/click")
    public ResponseEntity<String> trackClick(@RequestBody ArticleClickRequest dto) {
        userInteractionService.saveClick(dto.getUserId(), dto.getTitle(), dto.getCategory(), dto.getUrl());
        return ResponseEntity.ok("Click tracked");
    }
//    @PostMapping("/register")
//    public ResponseEntity<User>registerUser(@RequestBody User user){
//        return ResponseEntity.ok(userRepository.save(user));
//    }



}
