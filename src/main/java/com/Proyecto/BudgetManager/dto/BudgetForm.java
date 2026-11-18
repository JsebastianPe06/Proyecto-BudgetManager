package com.Proyecto.BudgetManager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Datos del formulario "Crear presupuesto" (US-03).
 * Viaja del controller al BudgetService.
 */
@Data
public class BudgetForm {

    private BigDecimal totalLimite;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaFinal;
}
