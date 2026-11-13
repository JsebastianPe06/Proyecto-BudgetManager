package com.Proyecto.BudgetManager.dto;

import com.Proyecto.BudgetManager.enums.BudgetEstado;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Estado de una categoría dentro de un presupuesto (US-03).
 * Ej: "Ocio: límite $500.000, gastado $400.000, 80%, PRECAUCION".
 * Viaja del BudgetService al controller (vista).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEstadoDTO {

    private Integer categoriaId;
    private String nombreCategoria;
    private BigDecimal limite;
    private BigDecimal gastado;
    private BigDecimal disponible;      // negativo si se pasó del límite
    private BigDecimal porcentajeUsado; // 0 a 100 (o más si se excedió)
    private BudgetEstado estado;
}
