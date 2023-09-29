package com.example.mySpring.controllers;

import com.example.mySpring.models.Article;
import com.example.mySpring.models.User;
import com.example.mySpring.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String addArticle(@AuthenticationPrincipal User user,
                             @RequestParam(name = "tittle", required = false, defaultValue = "Пустое поле") String tittle,
                             @RequestParam(name = "text", required = false, defaultValue = "Пустое поле") String text,
                             @RequestParam(name = "image", required = false, defaultValue = "Пустое поле") String image) {
        Article article = new Article(tittle, text, image, user);
        articleRepository.save(article);
        System.out.println(tittle);
        return "redirect:/";
    }

    @GetMapping("/article/{id}")
    public String chooseArticle(@PathVariable(value = "id") int id, Model model) {
        Article article = articleRepository.findById(id).orElse(new Article());
        model.addAttribute("article", article);
        return "article";
    }

    @GetMapping("/article/update/{id}")
    public String changeArticle(@PathVariable(value = "id") int id, Model model) {
        Article article = articleRepository.findById(id).orElse(new Article());
        model.addAttribute("article", article);
        return "update_article";
    }

    @PostMapping("/article/update/{id}")
    public String updateArticle(@PathVariable(value = "id") int id, Model model,
                                @RequestParam(name = "tittle", required = true, defaultValue = "Пустое поле") String tittle,
                                @RequestParam(name = "text", required = true, defaultValue = "Пустое поле") String text,
                                @RequestParam(name = "image", required = true, defaultValue = "Пустое поле") String image) {

        Article article = articleRepository.findById(id).orElse(new Article());
        article.setTittle(tittle);
        article.setText(text);
        article.setImage(image);
        articleRepository.save(article);
        return "redirect:/article/" + id;
    }

    @PostMapping("/article/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        Article article = articleRepository.findById(id).orElse(new Article());
        articleRepository.delete(article);
        return "redirect:/";
    }

}
