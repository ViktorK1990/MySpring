package com.example.mySpring.controllers;

import com.example.mySpring.models.Image;
import com.example.mySpring.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/photo")
    public String photo(Model model) {
        Iterable<Image> images = imageRepository.findAll();
        model.addAttribute("pictures", images);
        return "photo";
    }
}
