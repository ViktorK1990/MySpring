package com.example.mySpring.controllers;

import com.example.mySpring.models.Article;
import com.example.mySpring.models.Image;
import com.example.mySpring.repo.ArticleRepository;
import com.example.mySpring.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.modelmbean.ModelMBeanOperationInfo;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ImageRepository imageRepository;



    @GetMapping
    public String index(@RequestParam(name = "user", required = false, defaultValue = "пользователь") String param, Model model) {
        model.addAttribute("name", param);
        Iterable<Article> article = articleRepository.findAll();
        model.addAttribute("article", article);
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model) {
        Image image = imageRepository.findById(1).orElse(new Image(1," https://catherineasquithgallery.com/uploads/posts/2021-03/1614587275_6-p-kartinka-telefon-na-belom-fone-6.png"));
        model.addAttribute("img", image);
        return "about";
    }
}

