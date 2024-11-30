package com.example.otp.generation.controller;

import com.example.otp.generation.entity.News;
import com.example.otp.generation.dto.NewsDto;
import com.example.otp.generation.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin("*")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @PostMapping
    public ResponseEntity<News> addNews(@RequestBody NewsDto news) {
        return ResponseEntity.ok(newsService.addNews(news));
    }
}
