package com.Proyecto.BudgetManager.controller;

import com.Proyecto.BudgetManager.dto.MonthlyReport;
import com.Proyecto.BudgetManager.service.ReportService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public String view(@RequestParam(required = false) Integer mes,
                    @RequestParam(required = false) Integer anio, Authentication auth, Model model) {
        LocalDate hoy = LocalDate.now();
        int m = (mes != null) ? mes : hoy.getMonthValue();
        int a = (anio != null) ? anio : hoy.getYear();
        MonthlyReport report = reportService.generateMonthlyReport(auth.getName(), m, a);
        model.addAttribute("report", report);
        model.addAttribute("conciencia", reportService.getNivelConciencia(auth.getName(), m, a));
        return "reports/monthly";
    }
}
