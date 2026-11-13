package com.Proyecto.BudgetManager.dto;

import com.Proyecto.BudgetManager.enums.PrioridadGasto;
import com.Proyecto.BudgetManager.enums.TipoGasto;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Datos del formulario "Registrar gasto".
 * Viaja del controller (vista) al TransactionService.
 */
@Data
public class GastoForm {

    private String descripcion;
    private BigDecimal monto;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;

    private Integer categoriaId;
    private PrioridadGasto prioridad;
    private TipoGasto tipoGasto;
}
