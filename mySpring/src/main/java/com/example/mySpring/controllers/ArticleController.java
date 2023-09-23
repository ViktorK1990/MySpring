package com.example.mySpring.controllers;

import com.example.mySpring.models.Article;
import com.example.mySpring.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping
    public String index(@RequestParam(name = "user", required = false, defaultValue = "пользователь") String param, Model model) {
        model.addAttribute("name", param);
        Iterable<Article> article = articleRepository.findAll();
        model.addAttribute("article", article);
        return "index";
    }
}
