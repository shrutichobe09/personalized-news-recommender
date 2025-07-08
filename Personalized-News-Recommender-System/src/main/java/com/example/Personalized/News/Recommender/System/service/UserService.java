package com.example.Personalized.News.Recommender.System.service;

import com.example.Personalized.News.Recommender.System.dto.RecommendationRequestDto;
import com.example.Personalized.News.Recommender.System.model.User;
import com.example.Personalized.News.Recommender.System.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public User registerUser( RecommendationRequestDto dto){
        Optional<User>existingUser=userRepository.findByUserName(dto.getUsername());
        if (existingUser.isPresent()){
            throw  new RuntimeException("user already present");
        }
        User user=new User();
        user.setUserName(dto.getUsername());
        user.setInterests(dto.getInterests());


        return userRepository.save(user);


    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }
}
