package com.example.Personalized.News.Recommender.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequestDto {
  private Long userId;
  private String username;
  private List<String> interests;
  private List<ArticleDTO> recentArticles;
}
