package com.Proyecto.BudgetManager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Datos del formulario "Registrar ingreso".
 * Viaja del controller al TransactionService.
 */
@Data
public class IngresoForm {

    private String descripcion;
    private BigDecimal monto;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;

    private Integer categoriaId;
}
