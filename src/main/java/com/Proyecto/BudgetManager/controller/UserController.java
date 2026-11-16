package com.Proyecto.BudgetManager.controller;

import com.Proyecto.BudgetManager.service.UserService;
import com.Proyecto.BudgetManager.dto.UserProfileDTO;
import com.Proyecto.BudgetManager.exception.BusinessException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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
    public String update(@Valid @ModelAttribute("profile") UserProfileDTO dto,
            BindingResult errors,Authentication auth,Model model, HttpServletRequest request)
            throws ServletException
            {
                if (errors.hasErrors()) return "user/profile";
            try {
                boolean emailChanged = userService.updateProfile(auth.getName(), dto);
            if (emailChanged) {
                request.logout();
                return "redirect:/auth/login?emailChanged";
            }
            return "redirect:/profile?ok";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            return "user/profile";
        }
    }
}
