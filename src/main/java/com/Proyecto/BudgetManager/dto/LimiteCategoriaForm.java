package com.Proyecto.BudgetManager.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * Datos del formulario "Asignar límite a una categoría" (US-03).
 * Ej: en el presupuesto de noviembre, "ocio" no puede pasar de $500.000.
 * Viaja del controller al BudgetService.
 */
@Data
public class LimiteCategoriaForm {

    private Integer budgetId;
    private Integer categoriaId;
    private BigDecimal limite;
}
