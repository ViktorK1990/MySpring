package com.example.mySpring.controllers;

import com.example.mySpring.models.Article;
import com.example.mySpring.models.User;
import com.example.mySpring.repo.ArticleRepository;
import com.example.mySpring.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/admin")
    public String admin(Principal principal, Model model) {
        String result = "";
        String checkAdmin = userRepository.findByUsername(principal.getName()).getRoles().toString();
        if(checkAdmin.equals("[ADMIN]")) {
            Iterable<User> users = userRepository.findAll();
            model.addAttribute("users", users);
            result = "admin";
        } else {
            result = "errorAdmin";
        }
        return result;
    }

    @GetMapping("users/allArticles/{id}")
    public String getUserArticles(@PathVariable(value = "id") int id, Model model) {
        User user = userRepository.findById(id).orElse(new User());
        Iterable<Article> articles = user.getArticle();
        model.addAttribute("articles", articles);
        return "userArticles";
    }
}
