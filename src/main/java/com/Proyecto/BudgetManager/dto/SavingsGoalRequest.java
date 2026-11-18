package com.Proyecto.BudgetManager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;

public record SavingsGoalRequest(
    @NotBlank(message = "El nombre de la meta es obligatorio")
    @Size(max = 100, message = "El nombre es muy largo")
    String name,

    @NotNull(message = "El monto objetivo es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    BigDecimal targetAmount,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String deadline // Lo manejamos como String para evitar problemas con SQLite
) {}
