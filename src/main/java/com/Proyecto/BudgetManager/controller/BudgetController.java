package com.Proyecto.BudgetManager.controller;

import com.Proyecto.BudgetManager.dto.BudgetRequest;
import com.Proyecto.BudgetManager.dto.CategoryLimitRequest;
import com.Proyecto.BudgetManager.exception.BusinessException;
import com.Proyecto.BudgetManager.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/budget")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public String view(@RequestParam(required = false) Integer mes,
                    @RequestParam(required = false) Integer anio,
                    Authentication auth, Model model) {
        LocalDate hoy = LocalDate.now();
        int mesActual = (mes != null) ? mes : hoy.getMonthValue();
        int anioActual = (anio != null) ? anio : hoy.getYear();
        var budget = budgetService.getBudget(auth.getName(), mesActual, anioActual);
        model.addAttribute("budget", budget);
        model.addAttribute("mes", mesActual);
        model.addAttribute("anio", anioActual);
        if (budget != null) {
            model.addAttribute("estados", budgetService.getCategoryStatus(auth.getName(), mesActual, anioActual));
        } else {
            model.addAttribute("estados", null);
        }
        return "budget/view";
    }

    @GetMapping("/new")
    public String showForm(@RequestParam(required = false) Integer mes,
                        @RequestParam(required = false) Integer anio,Model model) {
        int m = (mes != null) ? mes : LocalDate.now().getMonthValue();
        int a = (anio != null) ? anio : LocalDate.now().getYear();
        model.addAttribute("form", new BudgetRequest(m, a, null));
        return "budget/form";
    }

    @PostMapping("/new")
    public String save(@Valid @ModelAttribute("form") BudgetRequest req, BindingResult errors, Authentication auth, Model model) {
        if (errors.hasErrors()) return "budget/form";
        try {
            budgetService.createBudget(auth.getName(), req);
            return "redirect:/budget?ok";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            return "budget/form";
        }
    }

    @GetMapping("/limits")
    public String showLimitsForm(Authentication auth, Model model) {
        int mesActual = LocalDate.now().getMonthValue();
        int anioActual = LocalDate.now().getYear();
        var budget = budgetService.getBudget(auth.getName(), mesActual, anioActual);
        if (budget == null) {
            return "redirect:/budget/new"; // Si no hay presupuesto, que lo cree primero
        }
        model.addAttribute("form", new CategoryLimitRequest(null, null));
        model.addAttribute("categories", com.Proyecto.BudgetManager.enums.Category.values());
        model.addAttribute("mes", mesActual);
        model.addAttribute("anio", anioActual);
        return "budget/limits-form";
    }

    @PostMapping("/limits")
    public String saveLimit(@Valid @ModelAttribute("form") CategoryLimitRequest req, BindingResult errors, Authentication auth, Model model) {
        int mesActual = LocalDate.now().getMonthValue();
        int anioActual = LocalDate.now().getYear();
        if (errors.hasErrors()) {
            model.addAttribute("categories", com.Proyecto.BudgetManager.enums.Category.values());
            return "budget/limits-form";
        }
        try {
            budgetService.addCategoryLimit(auth.getName(), mesActual, anioActual, req);
            return "redirect:/budget?ok";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("categories", com.Proyecto.BudgetManager.enums.Category.values());
            return "budget/limits-form";
        }
    }
}