package com.Proyecto.BudgetManager.domain.domainTransaction;

import java.time.LocalDate;
import java.time.YearMonth;

public final class GeneradorDeRecurrentes {
    /**
     * Calcula la fecha de ejecución para un gasto recurrente en un mes y año dados.
     * Si el día solicitado excede los días del mes, devuelve el último día del mes.
     */
    public static LocalDate calcularFechaEjecucion(int diaRecurrente, int mes, int anio) {
        if (diaRecurrente < 1 || diaRecurrente > 31) {
            throw new IllegalArgumentException("El día recurrente debe estar entre 1 y 31");
        }
        
        YearMonth yearMonth = YearMonth.of(anio, mes);
        int ultimoDiaDelMes = yearMonth.lengthOfMonth();
        
        int diaFinal = Math.min(diaRecurrente, ultimoDiaDelMes);
        
        return LocalDate.of(anio, mes, diaFinal);
    }
}
