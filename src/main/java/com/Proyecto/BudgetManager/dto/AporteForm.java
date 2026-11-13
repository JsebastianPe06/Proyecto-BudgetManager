package com.Proyecto.BudgetManager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Datos del formulario "Registrar aporte a una meta".
 * Viaja del controller al SavingGoalService.
 */
@Data
public class AporteForm {

    private Integer metaId;
    private BigDecimal monto;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;
}
