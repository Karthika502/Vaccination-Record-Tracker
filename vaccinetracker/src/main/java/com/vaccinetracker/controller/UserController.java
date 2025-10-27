package com.vaccinetracker.controller;

import com.vaccinetracker.model.User;
import com.vaccinetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Show list of users
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";  // must match users.html
    }

    // Show add-user form
    @GetMapping("/users/new")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";  // must match add-user.html
    }

    // Save user
    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    // Root redirect
    @GetMapping("/")
    public String home() {
        return "redirect:/users";
    }
}
