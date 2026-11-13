package com.Proyecto.BudgetManager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Datos del formulario "Crear meta de ahorro".
 * Viaja del controller al SavingGoalService.
 */
@Data
public class MetaForm {

    private String nombre;
    private BigDecimal objetivo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaInicial;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaFinal;
}
