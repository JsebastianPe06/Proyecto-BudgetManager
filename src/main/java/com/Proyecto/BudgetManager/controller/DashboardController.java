package com.Proyecto.BudgetManager.controller;

import com.Proyecto.BudgetManager.service.BudgetService;
import com.Proyecto.BudgetManager.service.SavingsGoalService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class DashboardController {
    private final BudgetService budgetService;
    private final SavingsGoalService savingsGoalService;

    public DashboardController(BudgetService budgetService, SavingsGoalService savingsGoalService) {
        this.budgetService = budgetService;
        this.savingsGoalService = savingsGoalService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        int mesActual = LocalDate.now().getMonthValue();
        int anioActual = LocalDate.now().getYear();
        var budget = budgetService.getBudget(auth.getName(), mesActual, anioActual);
        model.addAttribute("budget", budget);
        if (budget != null) {
            model.addAttribute("estadosCategorias", budgetService.getCategoryStatus(auth.getName(), mesActual, anioActual));
        }
        var metas = savingsGoalService.getGoals(auth.getName());
        model.addAttribute("metas", metas);
        return "dashboard";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/dashboard";
    }
}
