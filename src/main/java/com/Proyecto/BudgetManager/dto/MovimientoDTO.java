package com.Proyecto.BudgetManager.dto;

import com.Proyecto.BudgetManager.enums.PrioridadGasto;
import com.Proyecto.BudgetManager.enums.TipoGasto;
import com.Proyecto.BudgetManager.enums.TipoMovimiento;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Un movimiento (gasto o ingreso) para mostrar en historial y filtros (FR-03, FR-07).
 * Viaja del TransactionService al controller (vista).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {

    private Integer id;
    private String descripcion;
    private BigDecimal monto;
    private LocalDate fecha;
    private TipoMovimiento tipo;
    private String nombreCategoria;
    private PrioridadGasto prioridad;   // null si es ingreso
    private TipoGasto tipoGasto;        // null si es ingreso
}
