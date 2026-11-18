package com.Proyecto.BudgetManager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record BudgetRequest(
    @NotNull(message = "El mes es obligatorio")
    @Min(value = 1, message = "Mes inválido")
    @Max(value = 12, message = "Mes inválido")
    Integer month,

    @NotNull(message = "El año es obligatorio")
    Integer year,

    @NotNull(message = "El monto total es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    BigDecimal totalAmount
) {}