package com.example.Personalized.News.Recommender.System.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private String title;
    private String description;
    private String url;
    private String category;
}
