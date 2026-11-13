package com.Proyecto.BudgetManager.domain;

import com.Proyecto.BudgetManager.entity.fecha;
import java.time.LocalDate;

public class DateManager {
    
    /** Convierte la fecha del formulario (LocalDate) a la clase fecha del equipo. */
    public static fecha aFecha(LocalDate date)
    {
        if (date == null)
        {
            return null;
        }
        return new fecha(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }

    /** Convierte la clase fecha del equipo a LocalDate (para DTOs y consultas). */
    public static LocalDate aLocalDate(fecha date)
    {
        if (date == null)
        {
            return null;
        }
        return LocalDate.of(date.getYear(), date.getMes(), date.getDia());
    }
    
}
