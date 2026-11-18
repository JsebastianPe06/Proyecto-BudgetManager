package com.Proyecto.BudgetManager.controller;

import com.Proyecto.BudgetManager.dto.SavingsGoalRequest;
import com.Proyecto.BudgetManager.exception.BusinessException;
import com.Proyecto.BudgetManager.service.SavingsGoalService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/goals")
public class SavingsGoalController {
    private final SavingsGoalService goalService;

    public SavingsGoalController(SavingsGoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public String list(Authentication auth, Model model) {
        model.addAttribute("goals", goalService.getGoals(auth.getName()));
        return "goals/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("form", new SavingsGoalRequest("", null, ""));
        return "goals/form";
    }

    @PostMapping("/new")
    public String save(@Valid @ModelAttribute("form") SavingsGoalRequest req, BindingResult errors, Authentication auth, Model model) {
        if (errors.hasErrors()) return "goals/form";
        try {
            goalService.createGoal(auth.getName(), req);
            return "redirect:/goals?ok";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            return "goals/form";
        }
    }

    @PostMapping("/add-funds")
    public String addFunds(@RequestParam Long goalId, @RequestParam BigDecimal amount, Authentication auth, RedirectAttributes redirectAttributes) {
        try {
            goalService.addFunds(auth.getName(), goalId, amount);
            redirectAttributes.addFlashAttribute("success", "Fondos abonados correctamente.");
        } catch (BusinessException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/goals";
    }
}
