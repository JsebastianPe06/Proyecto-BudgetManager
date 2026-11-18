package com.Proyecto.BudgetManager.dto;

import com.Proyecto.BudgetManager.enums.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CategoryLimitRequest(
    @NotNull(message = "La categoría es obligatoria")
    Category category,

    @NotNull(message = "El límite es obligatorio")
    @DecimalMin(value = "0.01", message = "El límite debe ser mayor a cero")
    BigDecimal limitAmount
) {}