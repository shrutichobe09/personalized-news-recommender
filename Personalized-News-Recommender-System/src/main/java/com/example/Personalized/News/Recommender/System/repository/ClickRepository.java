package com.example.Personalized.News.Recommender.System.repository;

import com.example.Personalized.News.Recommender.System.model.ArticleClick;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickRepository extends JpaRepository<ArticleClick,Long> {
}
