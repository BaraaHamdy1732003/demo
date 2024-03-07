package com.example.demo.controllers;

import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public String getUsersPage(Model model){
        model.addAttribute("usersList",userService.getAllUsers());
        return "users_page";

    }
}
