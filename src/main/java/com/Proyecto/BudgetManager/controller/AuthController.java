package com.Proyecto.BudgetManager.controller;

import com.Proyecto.BudgetManager.service.AuthService;
import com.Proyecto.BudgetManager.dto.RegisterRequest;
import com.Proyecto.BudgetManager.exception.BusinessException;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    // Muestra el formulario de login. El POST lo maneja Spring Security en /login
    @GetMapping("/login")
    public String loginForm() {
        return "auth/login";
    }

    // Muestra el formulario de registro vacío
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("form", new RegisterRequest("", "", ""));
        return "auth/register";
    }

    // Procesa el registro y delega en AuthService
    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute("form") RegisterRequest req,
                                BindingResult errors, Model model) {
        if (errors.hasErrors()) return "auth/register";
        try {
            authService.register(req);
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }
        return "redirect:/auth/login?registered";
    }
}
