package com.example.Personalized.News.Recommender.System.dto;

import lombok.Data;

@Data
public class ArticleClickRequest {
    private Long userId;
    private String title;
    private String category;
    private String url;
}
