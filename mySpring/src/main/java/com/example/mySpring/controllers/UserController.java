package com.example.mySpring.controllers;

import com.example.mySpring.models.Role;
import com.example.mySpring.models.User;
import com.example.mySpring.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reg")
    public String reg(@RequestParam(name = "error", required = false, defaultValue = "") String error, Model model) {
        if (error.equals("username")) {
            model.addAttribute("error", "Такой пользователь уже существует!");
        } else if (error.equals("password")) {
            model.addAttribute("error", "Слишком короткий пароль!");
        } else if (error.equals("email")) {
            model.addAttribute("error", "Некорректный email!");
        }
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(@RequestParam(name = "username", required = false, defaultValue = "") String username,
                          @RequestParam(name = "password", required = false, defaultValue = "") String password,
                          @RequestParam(name = "email", required = false, defaultValue = "") String email) {

        if (userRepository.findByUsername(username) != null) {
            return "redirect:/reg?error=username";
        } else if (password.length() < 5) {
            return "redirect:/reg?error=password";
        } else if (!email.contains("@")) {
            return "redirect:/reg?error=email";
        } else {
            password = passwordEncoder.encode(password);
            User user = new User(username, password, email, true, Collections.singleton(Role.USER));
            userRepository.save(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/user")
    public String user(@RequestParam(name = "change", required = false, defaultValue = "") String change, Principal principal, Model model) {
        if (change.equals("true")) {
            model.addAttribute("data", "Данные успешно изменены!");
        }
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        Iterable<Role> roles = List.of(Role.values());
        model.addAttribute("roles", roles);
        return "user";
    }



    @PostMapping("/user")
    public String changeData(Principal principal, User userForm) {
        User user = userRepository.findByUsername(principal.getName());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setEmail(userForm.getEmail());
        user.setRoles(userForm.getRoles());
        userRepository.save(user);
        return "redirect:/user?change=true";
    }
}
