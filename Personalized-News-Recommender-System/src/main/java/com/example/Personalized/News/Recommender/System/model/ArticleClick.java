package com.example.Personalized.News.Recommender.System.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Table(name = "articleclick")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ArticleClick {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String articleTitle;
    private String articleUrl;
    private String category;
    private LocalDateTime clickedAt;

}
