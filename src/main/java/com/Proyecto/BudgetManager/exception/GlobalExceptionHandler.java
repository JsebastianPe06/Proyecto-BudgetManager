package com.Proyecto.BudgetManager.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public String handle(BusinessException ex, Model m) {
        m.addAttribute("error", ex.getMessage());
        return "error/business"; //templates/error/business.html
    }
}