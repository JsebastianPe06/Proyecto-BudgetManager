package com.Proyecto.BudgetManager.controller;

import com.Proyecto.BudgetManager.exception.BusinessException;
import com.Proyecto.BudgetManager.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService) { this.adminService = adminService; }

    @PostMapping("/parameters")
    public String saveParam(@RequestParam String key,@RequestParam String value,Model model) {
        try {
            adminService.upsertParameter(key, value);
            return "redirect:/admin/parameters?ok";
        } catch (BusinessException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("params", adminService.findAllParameters());
            return "admin/parameters";   // vuelve a la vista, sin guardar
        }
    }

    @GetMapping("/parameters")
    public String parameters(Model model) {
        model.addAttribute("params", adminService.findAllParameters());
        return "admin/parameters";
    }

    @GetMapping
    public String panel() {
        return "admin/panel";
    }
}
