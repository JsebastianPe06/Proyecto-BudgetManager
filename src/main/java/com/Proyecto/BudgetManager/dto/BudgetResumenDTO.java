package com.Proyecto.BudgetManager.dto;

import com.Proyecto.BudgetManager.enums.BudgetEstado;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Resumen de un presupuesto: total y estado por categoría (US-03, FR-09).
 * Viaja del BudgetService al controller (vista).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetResumenDTO {

    private Integer budgetId;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;

    private BigDecimal totalLimite;
    private BigDecimal totalGastado;
    private BigDecimal disponible;      // negativo si se pasó del límite
    private BigDecimal porcentajeUsado;
    private BudgetEstado estado;

    private List<CategoriaEstadoDTO> categorias = new ArrayList<>();
}
