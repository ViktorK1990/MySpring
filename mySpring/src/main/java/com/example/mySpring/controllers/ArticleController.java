package com.example.mySpring.controllers;

import com.example.mySpring.models.Article;
import com.example.mySpring.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/add_article")
    public String add(Model model) {
        return "add_article";
    }

    @PostMapping("/add_article")
    public String addArticle(@RequestParam(name = "text", required = false, defaultValue = "`1") String tittle,
                             @RequestParam(name = "text", required = false, defaultValue = "1") String text,
                             @RequestParam(name = "image", required = false, defaultValue = "1") String image) {
        Article article = new Article(tittle, text, image);
        articleRepository.save(article);
        System.out.println(tittle);
        return "redirect:/";
    }

    @GetMapping("/article/update/{id}")
    public String chooseArticle(@PathVariable(value = "id") int id, Model model) {
        Article article = articleRepository.findById(id).orElse(new Article());
        model.addAttribute("article", article);
        return "/update_article";
    }

    @PostMapping("/article/update/{id}")
    public String updateArticle(@PathVariable(value = "id") int id, Model model,
                                @RequestParam(name = "tittle", required = true, defaultValue = "3") String tittle,
                                @RequestParam(name = "text", required = true, defaultValue = "3") String text,
                                @RequestParam(name = "image", required = true, defaultValue = "3") String image) {

        Article article = articleRepository.findById(id).orElse(new Article());
        article.setTittle(tittle);
        article.setText(text);
        article.setImage(image);
        articleRepository.save(article);
        return "redirect:/";
    }

}
