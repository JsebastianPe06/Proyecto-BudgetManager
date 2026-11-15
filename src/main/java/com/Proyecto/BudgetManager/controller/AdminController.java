package com.Proyecto.BudgetManager.controller;

import com.Proyecto.BudgetManager.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService) { this.adminService = adminService; }

    @PostMapping("/parameters")
    public String saveParam(@RequestParam String key, @RequestParam String value) {
        adminService.upsertParameter(key, value);
        return "redirect:/admin/parameters?ok";
    }
}
