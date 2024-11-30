package com.example.otp.generation.service;

import com.example.otp.generation.entity.News;
import com.example.otp.generation.dto.NewsDto;
import com.example.otp.generation.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News addNews(NewsDto news) {

        return newsRepository.save( News.builder().timestamp(LocalDateTime.now()).author(news.getAuthor()).title(news.getTitle()).content(news.getContent()).build());
    }
}
