package com.Proyecto.BudgetManager.dto;

import com.Proyecto.BudgetManager.enums.Category;
import com.Proyecto.BudgetManager.enums.Priority;
import com.Proyecto.BudgetManager.enums.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
    @NotNull(message = "El tipo es obligatorio")
    TransactionType type,

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
    BigDecimal amount,

    @NotNull(message = "La categoría es obligatoria")
    Category category,

    @NotNull(message = "La prioridad es obligatoria")
    Priority priority,

    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate transactionDate,

    // CAMBIAMOS A Boolean (objeto) para que acepte null
    Boolean planned,
    Boolean recurrent,
    Integer recurrentDay
) {}
