package com.Proyecto.BudgetManager.controller;

import com.Proyecto.BudgetManager.service.UserService;
import com.Proyecto.BudgetManager.dto.UserProfileDTO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/profile")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public String view(Authentication auth, Model model) {
        model.addAttribute("profile", userService.getProfile(auth.getName()));
        return "user/profile";
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("profile") UserProfileDTO dto, Authentication auth) {
        userService.updateProfile(auth.getName(), dto);
        return "redirect:/profile?ok";
    }
}
