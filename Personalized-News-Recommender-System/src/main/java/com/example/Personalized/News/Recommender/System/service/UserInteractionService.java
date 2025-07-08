package com.example.Personalized.News.Recommender.System.service;

import com.example.Personalized.News.Recommender.System.model.ArticleClick;
import com.example.Personalized.News.Recommender.System.repository.ClickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserInteractionService {
    private final ClickRepository clickRepository;
    public void saveClick(Long userId,String title, String category ,String url){
        ArticleClick click=new ArticleClick();
         click.setUserId(userId);
         click.setArticleTitle(title);
         click.setCategory(category);
         click.setArticleUrl(url);
         click.setClickedAt(LocalDateTime.now());

         clickRepository.save(click);
    }
}
